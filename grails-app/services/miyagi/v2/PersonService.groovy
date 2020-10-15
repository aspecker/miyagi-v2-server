package miyagi.v2

import grails.gorm.transactions.Transactional

import java.time.Instant

@Transactional
class PersonService {

    def save(String firstName, String lastName, String dateOfBirth, String address) {
        Person person = new Person(firstName: firstName, lastName: lastName, dateOfBirth: dateOfBirth, address: address)
        person.save()
        return person
    }

    @Transactional(readOnly = true)
    def count() {
        Integer[] monthCount = new Integer[12];
        Arrays.fill(monthCount, 0);
        Person.list().collect {
            Integer thisMonth = it.dateOfBirth.getMonth();
            monthCount[thisMonth]++;
        }
        return monthCount;
    }

    def convertStringToDate(String dateString) {
        Instant i = Instant.parse(dateString);
        return Date.from(i)
    }

    def filter(String firstName, String lastName, String startDate, String endDate) {
        def c = Person.createCriteria()
        def result = c.list {
            if (firstName) {
                like ("firstName", "%"+firstName+"%")
            }
            if (lastName) {
                like("lastName", "%"+lastName+"%")
            }
            if (startDate != null && endDate != null) {
                between("dateOfBirth", convertStringToDate(startDate), convertStringToDate(endDate))
            } else if (startDate != null) {
                between("dateOfBirth", convertStringToDate(startDate), new Date())
            } else if (endDate != null) {
                between("dateOfBirth", Date.from(Instant.EPOCH), convertStringToDate(endDate))
            }
            order("lastName", "asc")
        }
        return result
    }
}
