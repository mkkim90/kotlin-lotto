package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
name("박재성")
company("우아한형제들")
skills {
soft("A passion for problem solving")
soft("Good communication skills")
hard("Kotlin")
}
languages {
"Korean" level 5
"English" level 3
}
}
 */
class DslTest {
    @ValueSource(strings = ["홍길동", "제이슨"])
    @ParameterizedTest
    fun name(name: String) {
        val person = introduce {
            name(name)
        }
        person.name shouldBe name
    }

    @Test
    fun company() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
    }

    @Test
    fun pair() {
        val pair1 = Pair(1, "one")
        val pair2 = 1 to "one"
        val pair3 = 1 of "one"
    }
}

infix fun String.level(i: Int) {

}

infix fun Int.of(value: String):Pair<Int, String> =  Pair(this, value)

fun introduce(block: PersonBuilder.() -> Unit): Person {
    // also, apply, run, let, with
    return PersonBuilder().apply { block() }.build()
}

fun skills(function: () -> Unit) {

}

fun soft(s: String) {
    TODO("Not yet implemented")
}

fun hard(s: String) {
    TODO("Not yet implemented")
}

fun languages(function: () -> Unit) {

}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null


    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun build(): Person {
        return Person(name, company)
    }
}

data class Person(val name: String, val company: String?)
