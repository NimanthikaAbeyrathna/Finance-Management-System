import Big from "big.js";

export class Account {
  constructor(
    public id:number,
    public accountType:string,
    public balance:Big,
    public userId:number
  ) {}
}
