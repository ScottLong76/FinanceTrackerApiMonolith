select bt1_0.id,
    bt1_0.amount,
    bt1_0.bank_id,
    bt1_0.deposit_id,
    bt1_0.description,
    bt1_0.payee,
    bt1_0.transaction_date,
    bt1_0.transaction_type,
    bt1_0.user_id,
    bt1_0.vendor_id
from bank_transaction bt1_0
where bt1_0.description = 'STOP & SHOP 0606 - CROMWELL	 CT'