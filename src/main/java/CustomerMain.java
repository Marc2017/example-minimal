import io.ebean.*;
import org.example.domain.Customer;
import org.example.domain.Street;
import org.example.domain.parent.Address;
import org.example.domain.parent.CustomerParent;


/**
 * When running tests in the IDE install the "Enhancement plugin".
 * <p>
 * http://ebean-orm.github.io/docs/setup/enhancement#ide
 */
public class CustomerMain {


  /**
   * Get the "default database" and save().
   */
  public static void main(String[] args) throws Exception {


    Database server = DB.getDefault();

    //Empty DB
    Transaction txn = server.beginTransaction();
    server.extended().delete(server.createQuery(Customer.class), txn);
    server.extended().delete(server.createQuery(Address.class), txn);
    server.extended().delete(server.createQuery(Street.class), txn);
    txn.commit();

    //=========================================================================
    // 1 - Create Data (Customer(dtype 1) -> Address(no dtype) -> Street(dtype 1)
    //=========================================================================
    txn = server.beginTransaction();
    Street street = new Street();
    server.save(street);
    Address address = new Address();
    address.setStreet(street);
    server.save(address);
    Customer customer = new Customer();
    customer.setAddress(address);
    server.save(customer);
    txn.commit();

    //=========================================================================
    // 2 - Read Data (no Cache)
    //=========================================================================
    reloadCustomer(server, customer, false);  //returns Street -> OK
    reloadCustomer(server, customer, false);  //returns Street -> OK

    //=========================================================================
    // 3 - Read Data (L2-Cache ON)
    //=========================================================================
    reloadCustomer(server, customer, true); //returns Street -> OK
    reloadCustomer(server, customer, true); //returns StreetParent -> NOT OK
  }


  public static void reloadCustomer(Database server, Customer customer, boolean l2Cache) {
    Transaction txn = null; //server.beginTransaction();

    //Load Customer via Query, L2Cache on/off
    Query<Customer> customerQuery = server.createQuery(Customer.class).where().eq("id", customer.getId()).query();
    customerQuery = customerQuery.setUseCache(l2Cache);
    customerQuery = customerQuery.setUseQueryCache(l2Cache);
    Customer customerReloaded = server.extended().findOne(customerQuery, txn);

    //Access Street by lazy Loading
    Class streetClassLazyLoaded = customerReloaded.getAddress().getStreet().getClass();

    //Show Cache Hits
    System.out.println("Class of Street (Cache on: "+l2Cache+"): "+ streetClassLazyLoaded + " | Cache Hit: "+ server.getServerCacheManager().getQueryCache(streetClassLazyLoaded).getStatistics(false).getHitCount());

  }

}
