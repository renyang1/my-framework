CREATE TABLE `ta`  (
                       `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                       `a_1` varchar(50) NOT NULL,
                       `a_2` int NOT NULL DEFAULT 0,
                       `a_3` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除  0未删除 1删除',
                       `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                       `create_user` VARCHAR(32) NOT NULL DEFAULT ''	COMMENT '创建人编号',
                       `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
                       `update_user` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '修改人编号',
                       PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '表a' ROW_FORMAT = Dynamic;


CREATE TABLE `tb`  (
                       `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                       `b_1` varchar(50) NOT NULL,
                       `b_2` int NOT NULL DEFAULT 0,
                       `b_3` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除  0未删除 1删除',
                       `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                       `create_user` VARCHAR(32) NOT NULL DEFAULT ''	COMMENT '创建人编号',
                       `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
                       `update_user` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '修改人编号',
                       PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '表b' ROW_FORMAT = Dynamic;