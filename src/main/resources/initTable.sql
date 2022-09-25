-- 消息表 结构
CREATE TABLE IF NOT EXISTS `broker_message` (
  `message_id` varchar(128) NOT NULL COMMENT '消息唯一 ID',
  `message` varchar(4000) COMMENT '消息内容，JSON格式',
  `try_count` int(4) DEFAULT 0 COMMENT '尝试次数',
  `status` varchar(10) DEFAULT '' COMMENT '消息状态',
  `next_retry` timestamp NOT NULL COMMENT '下次重复发送消息时间',
  `create_time` timestamp NOT NULL,
  `update_time` timestamp NOT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;