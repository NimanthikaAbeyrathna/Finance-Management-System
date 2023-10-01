import Big from "big.js";

export class LoanAccount {
  constructor(
    public id:number,
    public loanAmount:Big,
    public createDate:Date,
    public interestRate:Big,
    public accountId:number
  ) {}
}
