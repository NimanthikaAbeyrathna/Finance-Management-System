import Big from "big.js";

export class Savingaccount {
  constructor(
    public id:number,
    public createDate:Date,
    public interestRate:Big,
    public withdrawalLimit:Big,
    public accountId:number
  ) {}
}
