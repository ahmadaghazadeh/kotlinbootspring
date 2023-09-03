package util

import com.github.ahmadaghazadeh.bootspring.entity.Course

fun courseEntityList() = listOf(
    Course(null,
        "Build RestFul APis using SpringBoot and Kotlin", "Development",
        ),
    Course(null,
        "Build Reactive Microservices using Spring WebFlux/SpringBoot", "Development"
        ,
    ),
    Course(null,
        "Wiremock for Java Developers", "Development" ,
        )
)

inline fun<reified T> List<T>.isEqual(second: List<T>): Boolean {

    if (this.size != second.size) {
        return false
    }
    return this.toTypedArray() contentEquals second.toTypedArray()
}
