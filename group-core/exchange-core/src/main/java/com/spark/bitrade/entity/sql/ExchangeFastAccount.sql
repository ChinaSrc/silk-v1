-- auto Generated on 2019-03-27 11:00:11 
-- DROP TABLE IF EXISTS `exchange_fast_account`; 
CREATE TABLE exchange_fast_account(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `member_id` BIGINT NOT NULL DEFAULT 0 COMMENT '���˻�ID',
    `base_symbol` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '���һ��ұ�������,��CNYT��BT',
    `coin_symbol` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '���ұ������ƣ���BTC��LTC',
    `buy_adjust_rate` DECIMAL(8,4) NOT NULL DEFAULT 0 COMMENT '����ʱ�۸��ϵ�����,ȡֵ[0-1]�������û�����ʱ������ʵʱ�۵��ϵ��۸�ĸ���������',
    `sell_adjust_rate` DECIMAL(8,4) NOT NULL DEFAULT 0 COMMENT '����ʱ�۸��µ�������ȡֵ[0-1]�������û����ʱ������ʵʱ�۵��µ��۸�ĸ���������',
    `enable` INTEGER(12) NOT NULL DEFAULT 1 COMMENT '����״̬��1=����/2=��ֹ',
    `app_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '����',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT 'exchange_fast_account';

ALTER TABLE exchange_fast_account add create_time datetime(0) NULL DEFAULT NULL COMMENT '����ʱ��';
ALTER TABLE exchange_fast_account add update_time datetime(0) NULL DEFAULT NULL COMMENT '����ʱ��';