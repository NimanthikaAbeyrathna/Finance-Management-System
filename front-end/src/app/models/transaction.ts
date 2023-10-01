import Big from 'big.js';

export class Transaction {
  constructor(
    public id:number,
    public transactionType:string,
    public amount:Big,
    public timestamp:Date,
    public accountId:number
) {}
}
