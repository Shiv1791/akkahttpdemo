package com.knoldus.service
import com.knoldus.model.PostResponse


object CalculatorService {
  def getDetails(postResponse: PostResponse): String = {
    postResponse.operation  match {
      case "+" if(postResponse.operation.length>1) =>postResponse.numbers.sum.toString
      case "*" if(postResponse.operation.length>1) =>postResponse.numbers.product.toString
      case "-" if(postResponse.operation.length>1) =>postResponse.numbers.foldLeft(0){(acc, min ) => acc - min}.toString
      case "/" if(postResponse.operation.length>1) =>postResponse.numbers.foldLeft(0){(acc, min ) => acc / min}.toString
    }
  }
}