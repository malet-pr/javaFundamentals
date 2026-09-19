package org.acme.fundamentals.monadicComposition.ex1to5;

import org.springframework.stereotype.Component;
import org.acme.fundamentals.monadicComposition.ex1to5.model.Customer;
import org.acme.fundamentals.monadicComposition.ex1to5.model.Capital;
import org.acme.fundamentals.monadicComposition.ex1to5.model.Country;
import org.acme.fundamentals.monadicComposition.ex1to5.model.Address;
import java.util.Optional;

@Component 
public class Records {

    static Capital paris = new Capital("Paris");
    static Capital rome = new Capital("Rome");
    static Capital berlin = new Capital("Berlin");

    static Country france = new Country(Optional.of(paris));
    static Country italy = new Country(Optional.of(rome));
    static Country germany = new Country(Optional.of(berlin));
    static Country empty = new Country(Optional.empty());
    static Country noCapital = new Country(Optional.empty());

    static Address orleans = new Address("Orleans",france);
    static Address florence = new Address("Florence",italy);
    static Address bonn = new Address("Bonn",germany);
    static Address glasgow = new Address("Glasgow",empty);
    static Address barcelona = new Address("Barcelona",noCapital);

    static Customer peter = new Customer("Peter",orleans);
    static Customer david = new Customer("David",florence);
    static Customer anna = new Customer("Anna",bonn);
    static Customer liz = new Customer("Liz",glasgow);
    static Customer carol = new Customer("Carol",barcelona);
  

}


