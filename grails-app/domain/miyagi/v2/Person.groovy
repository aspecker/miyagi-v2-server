package miyagi.v2

class Person {

    static constraints = {

    }

    static mapping = {
        autoTimestamp true
    }
    String firstName
    String lastName
    Date dateOfBirth
    String address
}
