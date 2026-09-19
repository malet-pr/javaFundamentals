package org.acme.fundamentals.monadicComposition.ex1to5;

import org.acme.fundamentals.monadicComposition.ex1to5.model.Customer;
import org.springframework.stereotype.Service;
import static org.acme.fundamentals.monadicComposition.ex1to5.Records.*;

@Service 
public class DataService {

    public static Customer findCustomer(int id){
        switch (id) {
            case 1: return peter;
            case 2: return david;
            case 3: return anna;
            case 4: return liz;
            case 5: return carol;
            default: return null;
        }
    }

}
