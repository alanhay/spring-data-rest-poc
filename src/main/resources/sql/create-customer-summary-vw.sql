create OR REPLACE view customer_summary_data_vw as	
select
	c.id as customer_id, 
	count(o.id) as order_count, 
	sum(ol.quantity * ol.product_price) as order_total,
	max(o.date_received) as last_order_date
from 
	customers c
inner join 
	orders o on o.customer_id = c.id
inner join 
	order_lines ol on ol.order_id = o.id
group by 
	c.id;