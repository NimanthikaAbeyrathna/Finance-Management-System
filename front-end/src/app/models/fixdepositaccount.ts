import Big from "big.js";

export class Fixdepositaccount {
  constructor(
    public id:number,
    public depositAmount:Big,
    public createDate:Date,
    public maturityDate:Date,
    public interestRate:Big,
    public accountId:number
  ) {}
}
