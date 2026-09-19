package org.acme.fundamentals.monadicComposition.ex1to5;

import org.acme.fundamentals.monadicComposition.ex1to5.model.*;
import org.springframework.stereotype.Service;
import java.util.Optional;
import static org.acme.fundamentals.monadicComposition.ex1to5.DataService.findCustomer;

@Service
public class MonadicCompositionExercises1to5 {

    private void printCustomers() {
        Customer customer = findCustomer(3);
        System.out.println(customer);
    }

    private void printCapitals() {
        Customer customer = findCustomer(4);
        Optional<String> capital = Optional.of(customer)
                .map(Customer::getAddress)
                .map(Address::getCountry)
                .flatMap(Country::getCapital)
                .map(Capital::getName);
        System.out.println(capital);
    }

   private void printCapitals2() {
       CustomerB customer = new CustomerB("Lucas", Optional.empty());
       Optional<String> capital = Optional.of(customer)
               .flatMap(CustomerB::getAddress)
               .map(Address::getCountry)
               .flatMap(Country::getCapital)
               .map(Capital::getName);
       System.out.println(capital);
   }

   public void run(String... args) {
       printCustomers();
       printCapitals();
       printCapitals2();
   }


}
