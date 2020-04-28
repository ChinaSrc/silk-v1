-- auto Generated on 2019-03-27 10:26:36 
-- DROP TABLE IF EXISTS `exchange_fast_coin`;
CREATE TABLE exchange_fast_coin(
    `id` varchar(150) NOT NULL COMMENT 'id,������baseSymbol��coinSymbol��appId���',
    `base_symbol` VARCHAR(32) NOT NULL  COMMENT '���һ��ұ�������,��CNYT��BT',
    `coin_symbol` VARCHAR(32) NOT NULL  COMMENT '���ұ������ƣ���BTC��LTC',
    /*`rate_reference_symbol` VARCHAR(32) NOT NULL DEFAULT '' COMMENT 'ʵʱ���ʲο����׶�����',*/
    `enable` INTEGER(12) NOT NULL DEFAULT 1 COMMENT '����״̬��1=����/2=��ֹ',
    `app_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '����',
    `rate_reference_base_symbol` VARCHAR(32) NULL COMMENT '�һ�����ʵʱ���ʲο��������ƣ�����Ϊnull',
    `rate_reference_coin_symbol` VARCHAR(32) NULL COMMENT '�һ�����ʵʱ���ʲο��������ƣ�����Ϊnull',
    `buy_adjust_rate` DECIMAL(8,4) NOT NULL DEFAULT 0 COMMENT '����ʱ�۸��ϵ�Ĭ�ϱ���,ȡֵ[0-1]�������û�����ʱ������ʵʱ�۵��ϵ��۸�ĸ���������',
    `sell_adjust_rate` DECIMAL(8,4) NOT NULL DEFAULT 0 COMMENT '����ʱ�۸��µ�Ĭ�ϱ�����ȡֵ[0-1]�������û����ʱ������ʵʱ�۵��µ��۸�ĸ���������',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '���ұ�������';


ALTER TABLE exchange_fast_coin add create_time datetime(0) NULL DEFAULT NULL COMMENT '����ʱ��';
ALTER TABLE exchange_fast_coin add update_time datetime(0) NULL DEFAULT NULL COMMENT '����ʱ��';

ALTER TABLE exchange_fast_coin add base_symbol_fixed_rate DECIMAL(18,8) NOT NULL DEFAULT 0 COMMENT '�һ����ҵĹ̶�����';
ALTER TABLE exchange_fast_coin add coin_symbol_fixed_rate DECIMAL(18,8) NOT NULL DEFAULT 0 COMMENT '�һ����ֵĹ̶�����';
