package softuni.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.services.*;

@Component
public class Console implements CommandLineRunner {
    private final UserService userService;
    private final CountryService countryService;
    private final TownService townService;
    private final AlbumService albumService;
    private final PictureService pictureService;
    private final TagService tagService;
    private final UserAlbumService userAlbumService;

    @Autowired
    public Console(UserService userService,
                   CountryService countryService,
                   TownService townService,
                   AlbumService albumService,
                   PictureService pictureService,
                   TagService tagService,
                   UserAlbumService userAlbumService) {
        this.userService = userService;
        this.countryService = countryService;
        this.townService = townService;
        this.albumService = albumService;
        this.pictureService = pictureService;
        this.tagService = tagService;
        this.userAlbumService = userAlbumService;
    }

    @Override
    public void run(String... strings) throws Exception {

//        File file = new File("src/main/resources/nerd.png");
//        byte[] pic = new byte[(int) file.length()];
//        try {
//            FileInputStream fileInputStream = new FileInputStream(file);
//            fileInputStream.read(pic);
//            fileInputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        User user = new User();
//        user.setFirstName("Dimana");
//        user.setLastName("Toteva");
//        user.setUsername("nerd_lady");
//        user.setPassword("Nerd_Lady2");
//        user.setEmail("nerd_lady@nerd.com");
//        user.setPicture(pic);
//        user.setAge(20);
//        user.setDeleted(false);
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2015, 10, 10);
//        user.setRegistrationDate(calendar.getTime());
//        user.setLastTimeLoggedIn(new Date());
//        user.setFriends(new HashSet<>());
//
//        User userTwo = new User();
//        userTwo.setFirstName("Pesho");
//        userTwo.setLastName("Peshov");
//        userTwo.setUsername("pesho_peshkata");
//        userTwo.setPassword("pesH0_5sho");
//        userTwo.setEmail("pesho_peshkata@abv.bg");
//        userTwo.setPicture(pic);
//        userTwo.setAge(19);
//        userTwo.setDeleted(false);
//        calendar.set(2015, 6, 22);
//        userTwo.setRegistrationDate(calendar.getTime());
//        userTwo.setLastTimeLoggedIn(new Date());
//        userTwo.setFriends(new HashSet<>());
//        userTwo.getFriends().add(user);
//
//        Country country = new Country();
//        country.setName("Bulgaria");
//
//        Town town = new Town();
//        town.setName("Sofia");
//        town.setCountry(country);
//
//        Country countryTwo = new Country();
//        countryTwo.setName("Serbia");
//
//        Town townTwo = new Town();
//        townTwo.setCountry(countryTwo);
//        townTwo.setName("Belgrad");
//
//        this.countryService.persist(country);
//        this.townService.persist(town);
//        this.countryService.persist(countryTwo);
//        this.townService.persist(townTwo);
//
//        user.setBornTown(town);
//        user.setLivingTown(town);
//        userTwo.setBornTown(town);
//        userTwo.setLivingTown(townTwo);
//        // Invalid username
        //user.setUsername("2");

        // Invalid password
        //user.setPassword("nerdnerdnerd");

        //Invalid email
        //  user.setEmail("@@");


//        this.userService.persist(user);
//        this.userService.persist(userTwo);
//        user.getFriends().add(user);
//        this.userService.persist(user);
//
//        Picture picture = new Picture();
//        picture.setTitle("Nerd");
//        picture.setPath("src/main/resources/nerd.png");
//        picture.setCaption("NERD");
//
//        Album album = new Album();
//        album.setName("NERDY2");
//        album.setBackgroundColor(Color.BLUE);
//        album.setPublic(true);
//        album.setPictures(new HashSet<>());
//        album.setTags(new HashSet<>());
//        album.setUsers(new HashSet<>());
//
//        Tag tag = new Tag();
//        tag.setTag("#NewYear");
//        this.tagService.persist(tag);
//
//        this.pictureService.persist(picture);
//        this.albumService.persist(album);
//
//        album.getPictures().add(picture);
//        album.getTags().add(tag);
//        album.getUsers().add(user);
//        this.albumService.persist(album);

//        UserAlbumId userAlbumId = new UserAlbumId();
//        userAlbumId.setAlbum(album);
//        userAlbumId.setUser(user);
//
//        UserAlbum userAlbum = new UserAlbum();
//        userAlbum.setUserAlbumId(userAlbumId);
//        userAlbum.setRole(Role.OWNER);
//       this.userAlbumService.persist(userAlbum);
//          BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Enter email provider!");
//        String provider = reader.readLine();
//
//        List<User> usersWithProvider = this.userService.getUsersWithEmailProvider(provider);
//
//        for (User user1 : usersWithProvider) {
//            System.out.println(user1.getUsername() + " " + user1.getEmail());
//        }
//
//        System.out.printf("Enter size");
//        Integer size = Integer.parseInt(reader.readLine());
//        byte[] bities = new byte[size];
//        int countOfUserWithBiggerPicture = this.userService.getNumberOfUsersWithPictureGreaterThan(bities);
//
//        System.out.printf("%d users have profile pictures wider than %s pixels%n", countOfUserWithBiggerPicture, size);
//
//        System.out.println("Enter year:");
//        Integer year = Integer.parseInt(reader.readLine());
//        System.out.println("Enter month");
//        Integer month = Integer.parseInt(reader.readLine());
//        System.out.println("Enter day");
//        Integer day = Integer.parseInt(reader.readLine());
//
//        calendar.set(year, month, day);
//
//        List<User> usersToSet = this.userService.getUsersLastLoggedInBefore(calendar.getTime());
//
//        int count = 0;
//        for (User user1 : usersToSet) {
//            user1.setDeleted(true);
//            this.userService.persist(user1);
//            count++;
//        }
//        System.out.printf("%d users have been deleted%n", count);
//
//        System.out.println("Enter tag");
//
//        String tagName = reader.readLine();
//
//        tagName = TagTransformer.transform(tagName);
//
//        Tag tagToPersist = new Tag();
//        tagToPersist.setTag(tagName);
//
//        this.tagService.persist(tagToPersist);
//
//        System.out.println(tagName + " was added to database");
    }
}
