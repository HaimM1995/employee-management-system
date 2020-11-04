package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.model.Employee;
import server.repository.dto.EmployeeDTO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //employeeRepository.findAllByIdIsIn(new ArrayList<Integer>(Arrays.asList(1,2)))
    List<Employee> findAllByIdIsIn(List<Integer> id);

    List<Employee> findByFirstNameContains(String name);

    Optional<Employee> findById(Integer id);

    List<Employee> findByCreatedAtAfter(LocalDateTime date);

    //employeeRepository.findByCreatedAtAfter(LocalDateTime.now().minusMinutes(20))
    List<Employee> findByCreatedAtBefore(LocalDateTime date);

    public List<Employee> findByFirstNameContainingIgnoreCase(String title);

    // Search by first name or last name
//    List<Employee> findByFirstNameIsLikeOrLastNameIsLike(String term, String term2);

    //Find posts between today and 3 days ahead
    List<Employee> findByCreatedAtBetween(LocalDateTime from, Date to);

    @Query("select t FROM Employee t WHERE lower(t.lastName) like lower(concat('%', :lstName, '%'))")
    List<Employee> findByLastNameContainsIgnoreCase(@Param("lstName") String lstName);

    @Query("SELECT t FROM Employee t WHERE t.email like %:email%")
    List<Employee> findByEmailContains(@Param("email") String email);

    @Query("SELECT t FROM Employee t WHERE t.firstName = :firstName and t.lastName  = :lstName")
    List<Employee> findByFirstNameAndLastName(String firstName, @Param("lstName") String lastName);

    @Query("SELECT t FROM Employee t WHERE t.firstName = ?1")
    Optional<Employee> findByName(String name);

    @Query("select t FROM Employee t WHERE t.firstName = ?1 and t.lastName  = ?2")
    List<Employee> findByFirstNameAndLastNameNumbers(String firstName, String lastName);

    @Query("SELECT a FROM Employee a INNER JOIN a.employeeCar c WHERE c.color=:carColor")
    List<Employee> findByCarColor(@Param("carColor") String carColor);

    // Get the list of employee in reverse order.
    @Query("select a from Employee a order by a.id desc")
    List<Employee> employeesInReverseOrder();

    @Query("SELECT new server.repository.dto.EmployeeDTO" +
            "(a.firstName,a.lastName,a.email,a.createdAt) " +
            "FROM Employee a inner join a.factory f " +
            "WHERE f.factoryName = ?1")
    List<EmployeeDTO> getEmplyeesByFactoryName(String factoryName);

    @Query("SELECT new server.repository.dto.EmployeeDTO" +
            "(a.firstName," +
            " a.lastName," +
            "a.email," +
            " a.createdAt)" +
            " FROM Employee a inner join a.factory f " +
            "WHERE f.factoryName <> ?1")
    List<EmployeeDTO> getEmplyeesByNotFactoryName(@Param("factoryName") String factoryName);

    @Query(value = "SELECT a FROM Employee a order by rand() limit 1", nativeQuery = true)
    Employee getRandom();

    @Query("from Employee a where a.id in :userIds")
    List<Employee> findAllForList(@Param("userIds") List<Integer> userIds);

    // Works
//    @Query("select a from Article a where a.user.id in (select user.id from User user inner join com.melardev.spring.blogapi.entities.UserSubscription relation on user.id=relation.following.id where relation.follower.id=:userId)")
//    List<Article> findAllForFeed(@Param("userId") Long userId);

//    @Query("SELECT t.name, count(a) as tag_count from Article a " +
//            "INNER JOIN a.tags t " +
//            "WHERE a.id in :ids " +
//            "GROUP BY t.id " +
//            "ORDER BY tag_count DESC")
//    List<Object[]> countArticlesByTags(@Param("ids") List<Long> articleIds);

}
