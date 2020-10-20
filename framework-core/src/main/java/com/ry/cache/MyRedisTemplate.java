package com.ry.cache;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * StringRedisTemplate扩展类，目的一是方便使用，二是尽量保持函数名与Redis原生命令一致
 * <p>
 * 注意：此类不能与RedisTemplate混合使用，因POJO对象将全部直接采用Fastjson转成使用json字符串进行存储，而RedisTemplate采用Jackson进行序列化
 * 
 * @author renyang
 */
@Component
//@ConditionalOnBean(StringRedisTemplate.class)
public class MyRedisTemplate implements InitializingBean {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	private HashOperations<String, String, String> opsForHash;
	private ListOperations<String, String> opsForList;
	private SetOperations<String, String> opsForSet;
	private ZSetOperations<String, String> opsForZSet;

	// ----------- Normal Command -------------
	public boolean expire(String key, int timeout) {
		return stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
	}

	public boolean exist(String key) {
		return stringRedisTemplate.hasKey(key);
	}

	public boolean del(String key) {
		return stringRedisTemplate.delete(key);
	}

	public long del(Collection<String> keys) {
		if (CollectionUtils.isEmpty(keys)) {
			return 0L;
		}

		return stringRedisTemplate.delete(keys);
	}

	// ----------- String Command -------------
	public String get(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	public <T> T get(String key, Class<T> clazz) {
		String value = stringRedisTemplate.opsForValue().get(key);
		if (value == null) {
			return null;
		}

		return JSON.parseObject(value, clazz);
	}

	public void set(String key, String value) {
		if (value == null) {
			return;
		}

		stringRedisTemplate.opsForValue().set(key, value);
	}

	public void setObject(String key, Object value) {
		if (value == null) {
			return;
		}
		stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(value));
	}

	public void setex(String key, String value, long timeout) {
		this.setex(key, value, timeout, TimeUnit.SECONDS);
	}

	public void setexObject(String key, Object value, long timeout) {
		this.setexObject(key, value, timeout, TimeUnit.SECONDS);
	}

	public void setex(String key, String value, long timeout, TimeUnit unit) {
		if (timeout <= 0) {
			this.set(key, value);
			return;
		}

		stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
	}

	public void setexObject(String key, Object value, long timeout, TimeUnit unit) {
		if (timeout <= 0) {
			this.setObject(key, value);
			return;
		}

		stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(value), timeout, unit);
	}

	public boolean setnx(String key, String value) {
		if (value == null) {
			return false;
		}
		return stringRedisTemplate.opsForValue().setIfAbsent(key, value);
	}

	public boolean setnxObject(String key, Object value) {
		if (value == null) {
			return false;
		}
		return stringRedisTemplate.opsForValue().setIfAbsent(key, JSON.toJSONString(value));
	}

	public boolean setnx(String key, String value, long timeout) {
		return this.setnx(key, value, timeout, TimeUnit.SECONDS);
	}

	public boolean setnxObject(String key, Object value, long timeout) {
		return this.setnxObject(key, value, timeout, TimeUnit.SECONDS);
	}

	public boolean setnx(String key, String value, long timeout, TimeUnit unit) {
		if (value == null) {
			return false;
		}

		if (timeout <= 0) {
			return this.setnx(key, value);
		}

		return stringRedisTemplate.opsForValue().setIfAbsent(key, value, timeout, unit);
	}

	public boolean setnxObject(String key, Object value, long timeout, TimeUnit unit) {
		if (value == null) {
			return false;
		}
		if (timeout <= 0) {
			return this.setnxObject(key, value);
		}

		return stringRedisTemplate.opsForValue().setIfAbsent(key, JSON.toJSONString(value), timeout, unit);
	}

	public List<String> mget(Collection<String> keys) {
		return stringRedisTemplate.opsForValue().multiGet(keys);
	}

	public <T> List<T> mget(Collection<String> keys, Class<T> clazz) {
		return stringRedisTemplate.opsForValue().multiGet(keys).stream()
				.map(x -> x == null ? null : JSON.parseObject(x, clazz)).collect(Collectors.toList());
	}

	public void mset(Map<String, String> map) {
		if (CollectionUtils.isEmpty(map)) {
			return;
		}
		stringRedisTemplate.opsForValue().multiSet(map);
	}

	public <T> void msetObject(Map<String, T> map) {
		if (CollectionUtils.isEmpty(map)) {
			return;
		}

		stringRedisTemplate.opsForValue().multiSet(objectMap2StrMap(map));
	}

	/**
	 * 批量set，注意当任意一个key已存在时，所有key都不设置，返回false
	 *
	 * @param map
	 * @return
	 */
	public boolean msetnx(Map<String, String> map) {
		if (CollectionUtils.isEmpty(map)) {
			return false;
		}
		return stringRedisTemplate.opsForValue().multiSetIfAbsent(map);
	}

	/**
	 * 批量set，注意当任意一个key已存在时，所有key都不设置，返回false
	 *
	 * @param map
	 * @return
	 */
	public <T> boolean msetnxObject(Map<String, T> map) {
		if (CollectionUtils.isEmpty(map)) {
			return false;
		}
		return stringRedisTemplate.opsForValue().multiSetIfAbsent(objectMap2StrMap(map));
	}

	public long incr(String key) {
		return stringRedisTemplate.opsForValue().increment(key);
	}

	public long incrby(String key, long delta) {
		return stringRedisTemplate.opsForValue().increment(key, delta);
	}

	public double incrby(String key, double delta) {
		return stringRedisTemplate.opsForValue().increment(key, delta);
	}

	public long decr(String key) {
		return stringRedisTemplate.opsForValue().decrement(key);
	}

	public long decrby(String key, long delta) {
		return stringRedisTemplate.opsForValue().decrement(key, delta);
	}

	public String getset(String key, String value) {
		return stringRedisTemplate.opsForValue().getAndSet(key, value);
	}

	public <T> T getsetObject(String key, T value) {
		String oldValue = stringRedisTemplate.opsForValue().getAndSet(key, JSON.toJSONString(value));
		return oldValue == null ? null : JSON.parseObject(oldValue, (Class<T>) value.getClass());
	}

	// ----------- Hash Command -------------
	public boolean hexists(String key, String hashKey) {
		return opsForHash.hasKey(key, hashKey);
	}

	public String hget(String key, String hashKey) {
		return opsForHash.get(key, hashKey);
	}

	public <T> T hget(String key, String hashKey, Class<T> clazz) {
		String value = opsForHash.get(key, hashKey);
		return value == null ? null : JSON.parseObject(value, clazz);
	}

	public List<String> hmget(String key, Collection<String> hashKeys) {
		return opsForHash.multiGet(key, hashKeys);
	}

	public <T> List<T> hmget(String key, Collection<String> hashKeys, Class<T> clazz) {
		return opsForHash.multiGet(key, hashKeys).stream().map(x -> x == null ? null : JSON.parseObject(x, clazz))
				.collect(Collectors.toList());
	}

	public Map<String, String> hgetall(String key) {
		return opsForHash.entries(key);
	}

	public <T> Map<String, T> hgetall(String key, Class<T> clazz) {
		return strMap2ObjectMap(opsForHash.entries(key), clazz);
	}

	public void hset(String key, String hashKey, String value) {
		opsForHash.put(key, hashKey, value);
	}

	public void hsetObject(String key, String hashKey, Object value) {
		opsForHash.put(key, hashKey, JSON.toJSONString(value));
	}

	public boolean hsetnx(String key, String hashKey, String value) {
		return opsForHash.putIfAbsent(key, hashKey, value);
	}

	public boolean hsetnxObject(String key, String hashKey, Object value) {
		return opsForHash.putIfAbsent(key, hashKey, JSON.toJSONString(value));
	}

	public void hmset(String key, Map<String, String> map) {
		opsForHash.putAll(key, map);
	}

	public <T> void hmsetObject(String key, Map<String, T> map) {
		opsForHash.putAll(key, objectMap2StrMap(map));
	}

	public long hdel(String key, String... hashKeys) {
		return opsForHash.delete(key, hashKeys);
	}

	public long hdel(String key, Collection<String> hashKeys) {
		return opsForHash.delete(key, hashKeys.toArray());
	}

	public long hincrby(String key, String hashKey, long delta) {
		return opsForHash.increment(key, hashKey, delta);
	}

	public double hincrby(String key, String hashKey, double delta) {
		return opsForHash.increment(key, hashKey, delta);
	}

	public Set<String> hkeys(String key) {
		return opsForHash.keys(key);
	}

	public long hlen(String key) {
		return opsForHash.size(key);
	}

	/**
	 * 
	 * @param key
	 * @param count 每次获取数量，不允许超过1000
	 * @return
	 */
	public Cursor<Entry<String, String>> hscan(String key, long count) {
		Assert.isTrue(count <= 1000, "count(" + count + ") exceeds 1000.");

		return opsForHash.scan(key, new ScanOptions.ScanOptionsBuilder().count(count).build());
	}

	/**
	 *
	 * @param <T>
	 * @param key
	 * @param count   每次获取数量，不允许超过1000
	 * @param pattern
	 * @return
	 */
	public Cursor<Entry<String, String>> hscan(String key, long count, String pattern) {
		Assert.isTrue(count <= 1000, "count(" + count + ") exceeds 1000.");

		return opsForHash.scan(key, new ScanOptions.ScanOptionsBuilder().count(count).match(pattern).build());
	}

	// ----------- List Command -------------
	public long lpush(String key, String value) {
		return opsForList.leftPush(key, value);
	}

	public long lpushObject(String key, Object value) {
		return opsForList.leftPush(key, JSON.toJSONString(value));
	}

	public long lpush(String key, Collection<String> values) {
		return opsForList.leftPushAll(key, values);
	}

	public <T> long lpushObject(String key, Collection<T> values) {
		return opsForList.leftPushAll(key, values.stream().map(JSON::toJSONString).collect(Collectors.toList()));
	}

	public long lpushx(String key, String value) {
		return opsForList.leftPushIfPresent(key, value);
	}

	public long lpushxObject(String key, Object value) {
		return opsForList.leftPushIfPresent(key, JSON.toJSONString(value));
	}

	public long linsert(String key, String pivot, String value) {
		return opsForList.leftPush(key, pivot, value);
	}

	public long linsertObject(String key, Object pivot, Object value) {
		return opsForList.leftPush(key, JSON.toJSONString(pivot), JSON.toJSONString(value));
	}

	public String lpop(String key) {
		return opsForList.leftPop(key);
	}

	public <T> T lpop(String key, Class<T> clazz) {
		String value = opsForList.leftPop(key);
		return value == null ? null : JSON.parseObject(value, clazz);
	}

	public String blpop(String key, int timeout) {
		return opsForList.leftPop(key, timeout, TimeUnit.SECONDS);
	}

	public <T> T blpop(String key, int timeout, Class<T> clazz) {
		String value = opsForList.leftPop(key, timeout, TimeUnit.SECONDS);
		return value == null ? null : JSON.parseObject(value, clazz);
	}

	public long rpush(String key, String value) {
		return opsForList.rightPush(key, value);
	}

	public long rpushObject(String key, Object value) {
		return opsForList.rightPush(key, JSON.toJSONString(value));
	}

	public long rpush(String key, Collection<String> values) {
		return opsForList.rightPushAll(key, values);
	}

	public <T> long rpushObject(String key, Collection<T> values) {
		return opsForList.rightPushAll(key, values.stream().map(JSON::toJSONString).collect(Collectors.toList()));
	}

	public long rpushx(String key, String value) {
		return opsForList.rightPushIfPresent(key, value);
	}

	public long rpushxObject(String key, Object value) {
		return opsForList.rightPushIfPresent(key, JSON.toJSONString(value));
	}

	public String rpop(String key) {
		return opsForList.rightPop(key);
	}

	public <T> T rpop(String key, Class<T> clazz) {
		String value = opsForList.rightPop(key);
		return value == null ? null : JSON.parseObject(value, clazz);
	}

	public String brpop(String key, int timeout) {
		return opsForList.rightPop(key, timeout, TimeUnit.SECONDS);
	}

	public <T> T brpop(String key, int timeout, Class<T> clazz) {
		String value = opsForList.rightPop(key, timeout, TimeUnit.SECONDS);
		return value == null ? null : JSON.parseObject(value, clazz);
	}

	public String rpoplpush(String sourceKey, String destinationKey) {
		return opsForList.rightPopAndLeftPush(sourceKey, destinationKey);
	}

	public <T> T rpoplpush(String sourceKey, String destinationKey, Class<T> clazz) {
		String value = opsForList.rightPopAndLeftPush(sourceKey, destinationKey);
		return value == null ? null : JSON.parseObject(value, clazz);
	}

	public String brpoplpush(String sourceKey, String destinationKey, long timeout) {
		return opsForList.rightPopAndLeftPush(sourceKey, destinationKey, timeout, TimeUnit.SECONDS);
	}

	public <T> T brpoplpush(String sourceKey, String destinationKey, long timeout, Class<T> clazz) {
		String value = opsForList.rightPopAndLeftPush(sourceKey, destinationKey, timeout, TimeUnit.SECONDS);
		return value == null ? null : JSON.parseObject(value, clazz);
	}

	public String lindex(String key, long index) {
		return opsForList.index(key, index);
	}

	public <T> T lindex(String key, long index, Class<T> clazz) {
		String value = opsForList.index(key, index);
		return value == null ? null : JSON.parseObject(value, clazz);
	}

	public long llen(String key) {
		return opsForList.size(key);
	}

	public List<String> lrange(String key, long start, long end) {
		return opsForList.range(key, start, end);
	}

	public <T> List<T> lrange(String key, long start, long end, Class<T> clazz) {
		return opsForList.range(key, start, end).stream().map(x -> JSON.parseObject(x, clazz))
				.collect(Collectors.toList());
	}

	public long lrem(String key, long count, String value) {
		return opsForList.remove(key, count, value);
	}

	public long lremObject(String key, long count, Object value) {
		return opsForList.remove(key, count, JSON.toJSONString(value));
	}

	public void lset(String key, long index, String value) {
		opsForList.set(key, index, value);
	}

	public void lsetObject(String key, long index, Object value) {
		opsForList.set(key, index, JSON.toJSONString(value));
	}

	public void ltrim(String key, long start, long end) {
		opsForList.trim(key, start, end);
	}

	// ----------- Set Command -------------
	public long sadd(String key, String... value) {
		return opsForSet.add(key, value);
	}

	public long sadd(String key, Collection<String> values) {
		return opsForSet.add(key, values.toArray(new String[values.size()]));
	}

	public long saddObject(String key, Object value) {
		return opsForSet.add(key, JSON.toJSONString(value));
	}

	public <T> long saddObject(String key, Collection<T> values) {
		return opsForSet.add(key,
				values.stream().map(JSON::toJSONString).collect(Collectors.toList()).toArray(new String[0]));
	}

	public boolean sismember(String key, String value) {
		return opsForSet.isMember(key, value);
	}

	public boolean sismemberObject(String key, Object o) {
		return opsForSet.isMember(key, JSON.toJSONString(o));
	}

	public Set<String> smembers(String key) {
		return opsForSet.members(key);
	}

	public <T> Set<T> smembers(String key, Class<T> clazz) {
		return opsForSet.members(key).stream().map(x -> JSON.parseObject(x, clazz)).collect(Collectors.toSet());
	}

	public long scard(String key) {
		return opsForSet.size(key);
	}

	public long srem(String key, String... values) {
		return opsForSet.remove(key, values);
	}

	public long sremObject(String key, Object value) {
		return opsForSet.remove(key, JSON.toJSONString(value));
	}

	public <T> long sremObject(String key, Collection<T> values) {
		return opsForSet.remove(key, values.stream().map(JSON::toJSONString).collect(Collectors.toList()));
	}

	public String spop(String key) {
		return opsForSet.pop(key);
	}

	public <T> T spop(String key, Class<T> clazz) {
		String value = opsForSet.pop(key);
		return value == null ? null : JSON.parseObject(value, clazz);
	}

	public List<String> spop(String key, long count) {
		return opsForSet.pop(key, count);
	}

	public <T> List<T> spop(String key, long count, Class<T> clazz) {
		return opsForSet.pop(key, count).stream().map(x -> JSON.parseObject(x, clazz)).collect(Collectors.toList());
	}

	// ----------- ZSet Command -------------
	public boolean zadd(String key, String value, double score) {
		return opsForZSet.add(key, value, score);
	}

	public long zadd(String key, Set<TypedTuple<String>> tuples) {
		return opsForZSet.add(key, tuples);
	}

	public boolean zaddObject(String key, Object value, double score) {
		return opsForZSet.add(key, JSON.toJSONString(value), score);
	}

	public long zaddObject(String key, Set<TypedTuple<Object>> tuples) {
		return opsForZSet.add(key,
				tuples.stream().map(x -> new DefaultTypedTuple<String>(JSON.toJSONString(x.getValue()), x.getScore()))
						.collect(Collectors.toSet()));
	}

	public long zcard(String key) {
		return opsForZSet.size(key);
	}

	public long zcount(String key, double min, double max) {
		return opsForZSet.count(key, min, max);
	}

	public Double zscore(String key, String value) {
		return opsForZSet.score(key, value);
	}

	public Double zscoreObject(String key, Object value) {
		return opsForZSet.score(key, JSON.toJSONString(value));
	}

	public double zincrby(String key, String value, double delta) {
		return opsForZSet.incrementScore(key, value, delta);
	}

	public double zincrbyObject(String key, Object value, double delta) {
		return opsForZSet.incrementScore(key, JSON.toJSONString(value), delta);
	}

	public Set<String> zrange(String key, long start, long end) {
		return opsForZSet.range(key, start, end);
	}

	public <T> Set<T> zrange(String key, long start, long end, Class<T> clazz) {
		return opsForZSet.range(key, start, end).stream().map(x -> JSON.parseObject(x, clazz))
				.collect(Collectors.toSet());
	}

	public Set<String> zrangebyscore(String key, double min, double max) {
		return opsForZSet.rangeByScore(key, min, max);
	}

	public <T> Set<T> zrangebyscore(String key, double min, double max, Class<T> clazz) {
		return opsForZSet.rangeByScore(key, min, max).stream().map(x -> JSON.parseObject(x, clazz))
				.collect(Collectors.toSet());
	}

	public Set<String> zrevrange(String key, long start, long end) {
		return opsForZSet.reverseRange(key, start, end);
	}

	public <T> Set<T> zrevrange(String key, long start, long end, Class<T> clazz) {
		return opsForZSet.reverseRange(key, start, end).stream().map(x -> JSON.parseObject(x, clazz))
				.collect(Collectors.toSet());
	}

	public Set<String> zrevrangebyscore(String key, double min, double max) {
		return opsForZSet.reverseRangeByScore(key, min, max);
	}

	public <T> Set<T> zrevrangebyscore(String key, double min, double max, Class<T> clazz) {
		return opsForZSet.reverseRangeByScore(key, min, max).stream().map(x -> JSON.parseObject(x, clazz))
				.collect(Collectors.toSet());
	}

	public Long zrank(String key, String value) {
		return opsForZSet.rank(key, value);
	}

	public Long zrankObject(String key, Object value) {
		return opsForZSet.rank(key, JSON.toJSONString(value));
	}

	public Long zrevrank(String key, String value) {
		return opsForZSet.reverseRank(key, value);
	}

	public Long zrevrankObject(String key, Object value) {
		return opsForZSet.reverseRank(key, JSON.toJSONString(value));
	}

	public long zrem(String key, String... values) {
		return opsForZSet.remove(key, values);
	}

	public long zremObject(String key, Object value) {
		return opsForZSet.remove(key, JSON.toJSONString(value));
	}

	public <T> long zremObject(String key, Collection<T> values) {
		return opsForZSet.remove(key, values.stream().map(JSON::toJSONString).toArray());
	}

	public long zremrangebyrank(String key, long start, long end) {
		return opsForZSet.removeRange(key, start, end);
	}

	public long zremrangebyscore(String key, double min, double max) {
		return opsForZSet.removeRangeByScore(key, min, max);
	}

	public Set<TypedTuple<String>> zrangeByScoreWithScores(String key, double min, double max) {
		return opsForZSet.rangeByScoreWithScores(key, min, max);
	}

	public <T> Set<TypedTuple<T>> zrangeByScoreWithScores(String key, double min, double max, Class<T> clazz) {
		Set<TypedTuple<String>> rangeByScoreWithScores = zrangeByScoreWithScores(key, min, max);
		return rangeByScoreWithScores.stream()
				.map(x -> new DefaultTypedTuple<>(JSON.parseObject(x.getValue(), clazz), x.getScore()))
				.collect(Collectors.toSet());
	}


	// ----------- lua脚本 -------------
	public <T> T execute(RedisScript<T> redisScript, List<String> keys, String value) {

		return stringRedisTemplate.execute(redisScript, stringRedisTemplate.getValueSerializer(),
				null, keys, value);
//		return stringRedisTemplate.execute(redisScript, keys, value);

	}


	@Override
	public void afterPropertiesSet() throws Exception {
		if (stringRedisTemplate == null) {
			return;
		}
		opsForHash = stringRedisTemplate.opsForHash();
		opsForList = stringRedisTemplate.opsForList();
		opsForSet = stringRedisTemplate.opsForSet();
		opsForZSet = stringRedisTemplate.opsForZSet();
	}

	private <T> Map<String, String> objectMap2StrMap(Map<String, T> map) {
		Map<String, String> strMap = new HashMap<>(map.size());
		for (Entry<String, T> entry : map.entrySet()) {
			strMap.put(entry.getKey(), JSON.toJSONString(entry.getValue()));
		}
		return strMap;
	}

	private <T> Map<String, T> strMap2ObjectMap(Map<String, String> map, Class<T> clazz) {
		Map<String, T> objMap = new HashMap<>(map.size());
		for (Entry<String, String> entry : map.entrySet()) {
			objMap.put(entry.getKey(), JSON.parseObject(entry.getValue(), clazz));
		}
		return objMap;
	}
}