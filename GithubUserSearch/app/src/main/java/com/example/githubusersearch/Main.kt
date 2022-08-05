package com.example.githubusersearch

package com.example.githubusersearch

val data1: Int = 10                 // val : value : 변경 X
val data2 = 20
var data3 = 30                      // var : variable : 변경 O

val nullableData1: String? = null   // ? : 변수에 null 값을 대입하기 위해서 선언
var nullableData2: String? = null

const val myConst: Int = 10         // const : 처음에 대입한 초깃값을 변경하지 않고 그대로 사용하는 변수

object Main {                       // object 로 선언한 클래스 / 메서드에서만 변경 가능
    const val myConst1 = 10
}

fun sum(a:Int, b:Int) : Int{        // fun : 함수를 선언할때 사용 함수의 반환값을 지정해줌
    return a+b
}

fun sum1(a:Int, b:Int) : Unit{      // Unit : 반환값이 없을때 명시 (생략 가능)

}

fun sum2(a: Int, b:Int) : Int = a+b // 단일 표현 함수 (간결함)

fun add(a:Int, b:Int) : Int = a+b               // 함수 오버로딩
fun add(a:Char, b:Char) = print(a + "/" + b)    // 같은 이름의 함수의 매개변수 부분을 다르게 함
fun add(a:String, b:String) = println(a)

infix fun Int.myFun(x: Int) : Int{          // infix : 중위 표현식
    return x*x
}
class FunClass{
    infix fun infixFun(a: Int){             // infixFun call 을 출력하는 함수
        println("infixFun call...")
    }
}
fun main(args: Array<String>){
    val obj = FunClass()                    // 클래스와 변수 연결
    obj.infixFun(10)                     // FunClass 의 infixFun 함수 호출
    obj infixFun 10                         // 윗줄과 같은 뜻

    println(10 myFun 10)                    // x*x를 리턴하는 myFun 함수를 두 수 사이에 호출 (중위)
    println(10.myFun(10))                // 윗줄과 같은 뜻
}

