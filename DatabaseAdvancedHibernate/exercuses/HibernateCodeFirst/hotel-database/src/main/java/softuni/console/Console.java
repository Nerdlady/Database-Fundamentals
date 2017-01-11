package softuni.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.entities.*;
import softuni.services.interfaces.*;

import java.util.Date;

@Component
public class Console implements CommandLineRunner {

    private final EmployeeService employeeService;
    private final CustomerService customerService;
    private final RoomStatusService roomStatusService;
    private final RoomTypeService roomTypeService;
    private final BedTypeService bedTypeService;
    private final RoomService roomService;
    private final PaymentService paymentService;
    private final OccupancyService occupancyService;

    @Autowired
    public Console(EmployeeService employeeService,
                   CustomerService customerService,
                   RoomStatusService roomStatusService,
                   RoomTypeService roomTypeService,
                   BedTypeService bedTypeService,
                   RoomService roomService,
                   PaymentService paymentService,
                   OccupancyService occupancyService) {
        this.employeeService = employeeService;
        this.customerService = customerService;
        this.roomStatusService = roomStatusService;
        this.roomTypeService = roomTypeService;
        this.bedTypeService = bedTypeService;
        this.roomService = roomService;
        this.paymentService = paymentService;
        this.occupancyService = occupancyService;
    }

    @Override
    public void run(String... strings) throws Exception {
        Employee employee = new Employee();
        employee.setFirstName("Ivan");
        employee.setLastName("Ivanov");
        employee.setTitle("manager");

        this.employeeService.persist(employee);

        Customer customer = new Customer();
        customer.setAccountNumber(123456789L);
        customer.setFirstName("Misho");
        customer.setLastName("Mishev");
        customer.setPhoneNumber("+359777888999");

        this.customerService.persist(customer);

        RoomStatus roomStatus = new RoomStatus();
        roomStatus.setRoomStatus(10L);
        roomStatus.setNotes("Perfect");

        this.roomStatusService.persist(roomStatus);

        RoomType roomType = new RoomType();
        roomType.setType("2 beds");
        roomType.setNotes("With two single beds");

        this.roomTypeService.persist(roomType);

        BedType bedType = new BedType();
        bedType.setType("Single");

        this.bedTypeService.persist(bedType);

        Room room = new Room();
        room.setRoomNumber(303L);
        room.setBedType(bedType);
        room.setRoomStatus(roomStatus);
        room.setRoomType(roomType);
        room.setRate(5D);

        Room room2 = new Room();
        room2.setRoomNumber(304L);
        room2.setBedType(bedType);
        room2.setRoomStatus(roomStatus);
        room2.setRoomType(roomType);
        room2.setRate(5D);

        this.roomService.persist(room);
        this.roomService.persist(room2);

        Payment payment = new Payment();
        payment.setPaymentDate(new Date());
        payment.setAccountNumber(customer);
        payment.setTotalDays(5);
        payment.setFirstDateOccupied(new Date());

        this.paymentService.persist(payment);

        Occupancy occupancy = new Occupancy();
        occupancy.setDateOccupied(new Date());
        occupancy.setAccountNumber(customer);
        occupancy.setRoomNumber(room);
        occupancy.setPhoneCharge(25.1);

        this.occupancyService.persist(occupancy);
    }
}
