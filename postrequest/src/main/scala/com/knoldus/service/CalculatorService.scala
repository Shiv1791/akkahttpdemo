package com.knoldus.service
import com.knoldus.model.PostResponse


object CalculatorService {
  def getDetails(postResponse: PostResponse): String = {
    postResponse.operation  match {
      case "+"  =>postResponse.numbers.sum.toString
      case "*"  =>postResponse.numbers.product.toString
      case "-"  =>postResponse.numbers.foldLeft(0){(acc, min ) => acc - min}.toString
      case "/"  =>postResponse.numbers.foldLeft(0){(acc, min ) => acc / min}.toString
    }
  }
}