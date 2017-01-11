package app.console;

import app.domain.Author;
import app.domain.Book;
import app.domain.Category;
import app.domain.enums.AgeRestriction;
import app.domain.enums.EditionType;
import app.projections.ReducedBook;
import app.service.AuthorService;
import app.service.BookService;
import app.service.CategoryService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Transactional
public class ConsoleRunner implements CommandLineRunner {
    private final AuthorService authorService;

    private final BookService bookService;

    private final CategoryService categoryService;

    @Autowired
    public ConsoleRunner(BookService bookService, CategoryService categoryService, AuthorService authorService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }

    @Override
    public void run(String... strings) throws Exception {
        // this.seedDatabase();
        // Hibernate Code First - Bookshop
       // this.tasksFromBookshopLab();

        // Advanced Querying
       // this.taskFromAdvancedQueryingExercise();

        Author author = this.authorService.findAuthor(1L);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
//        String authorJson = gson.toJson(author);
//        File outputFile = new File("src/main/resources/author.json");
//        try (
//                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));
//        ) {
//            bufferedWriter.write(authorJson);
//        } catch (FileNotFoundException e){
//            e.printStackTrace();
//        }
//
//        File otputFile = new File("src/main/resources/author.json");
//        StringBuilder jsonData = new StringBuilder();
//        try (BufferedReader bfr = new BufferedReader(new FileReader(otputFile));
//        ) {
//            String line;
//            while ((line = bfr.readLine()) != null) {
//                jsonData.append(line);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        Author vazov = gson.fromJson(jsonData.toString(), Author.class);
//        vazov.getBooksByAuthor().forEach(b -> b.setAuthor(vazov));
//        this.authorService.save(vazov);
//
//        JAXBContext jaxbContext = JAXBContext.newInstance(author.getClass());
//        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
//        File inputXMLFile = new File("src/main/resources/author.xml");
//        jaxbMarshaller.marshal(author,inputXMLFile);
//        JAXBContext jaxbContext = JAXBContext.newInstance(Author.class);
//        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//        File outputXMLFile = new File("src/main/resources/author.xml");
//        Author authorFromXML = (Author) unmarshaller.unmarshal(outputXMLFile);
//        authorFromXML.getBooksByAuthor().forEach(b -> b.setAuthor(authorFromXML));
//        this.authorService.save(authorFromXML);
    }

    private void taskFromAdvancedQueryingExercise() throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Calendar calendar = Calendar.getInstance();
        //1
        String type = reader.readLine();
        AgeRestriction ageRestriction = AgeRestriction.valueOf(type.toUpperCase());

        Iterable<Book> booksByAgeRestriction = this.bookService.findBooksByAgeRestriction(ageRestriction);

        for (Book book : booksByAgeRestriction) {
            System.out.println(book.getTitle());
        }

        //2
        Iterable<Book> goldenBook = this.bookService.findByEditionTypeAndCopies(EditionType.GOLD,5000);

        for (Book book : goldenBook) {
            System.out.println(book.getTitle());
        }

        //3
        Iterable<Book> bookWithPriceLowerAndGreater = this.bookService.findByPriceLowerThanOrGreaterThan(new BigDecimal("5"),new BigDecimal("40"));

        for (Book book : bookWithPriceLowerAndGreater) {
            System.out.printf("%s - %s%n",book.getTitle(),book.getPrice());
        }

        //4
        Integer year = Integer.parseInt(reader.readLine());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date before = simpleDateFormat.parse(year.toString() + "-01-01");
        Date after = simpleDateFormat.parse(year.toString() + "-31-12");

        Iterable<Book> booksNotInYear = this.bookService.findByReleaseDateNot(before,after);

        for (Book book : booksNotInYear) {
            System.out.println(book.getTitle());
        }

        //5
        String[] names = reader.readLine().split("\\s+");
        Iterable<Category> categoriesByName = this.categoryService.findAllByNameIn(names);
        Iterable<Book> booksByCategories = this.bookService.findAllByCategoriesIn(categoriesByName);

        for (Book booksByCategory : booksByCategories) {
            System.out.println(booksByCategory.getTitle());
        }

        //6
        String dateInput = reader.readLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = dateFormat.parse(dateInput);

        Iterable<Book> booksBefore = this.bookService.findAllByReleaseDateBefore(date);

        for (Book book : booksBefore) {
            System.out.printf("%s - %s -%s%n",book.getTitle(),book.getEditionType().toString(),book.getPrice());
        }

        //7
        String pattern = reader.readLine();
        Iterable<Author> authorsEndsWith = this.authorService.findAllByFirstNameEndsWith(pattern);

        for (Author author : authorsEndsWith) {
            System.out.printf("%s %s%n",author.getFirstName(),author.getLastName());
        }

        //8
        String bookPattern = reader.readLine();

        Iterable<Book> booksContains = this.bookService.findAllByTitleContains(bookPattern.toLowerCase());

        for (Book book : booksContains) {
            System.out.println(book.getTitle());
        }

        //9
        String authorLastNamePattern = reader.readLine();
        Iterable<Book> booksByAuthorLastName = this.bookService.findAllByAuthorLastNameStartsWith(authorLastNamePattern.toLowerCase());
        for (Book book : booksByAuthorLastName) {
            System.out.printf("%s (%s %s)%n",book.getTitle(),book.getAuthor().getFirstName(),book.getAuthor().getLastName());
        }

        //10
        Integer titleLength = Integer.parseInt(reader.readLine());
        List<Book> booksWithTitleLength = (List<Book>) this.bookService.findAllByTitleLengthMoreThan(titleLength);
        System.out.println(booksWithTitleLength.size());

        //11
        Iterable<Object[]> objects = this.bookService.findSumOfCopies();
        for (Object[] object : objects) {
            Author author = (Author) object[0];
            long copies = (long) object[1];
            System.out.printf("%s %s- %d%n", author.getFirstName(),author.getLastName(), copies);
        }

        //12
        Iterable<Object[]> booksByCategriesWithProfit = this.bookService.findAllByCategoriesWithProfit();

        for (Object[] bookByCategriesWithProfit : booksByCategriesWithProfit) {
            Category category = (Category)bookByCategriesWithProfit[0];
            BigDecimal profit = (BigDecimal) bookByCategriesWithProfit[1];

            System.out.printf("%s - %s%n",category.getName(),profit);
        }

        //13
        Iterable<Object[]> obj = this.bookService.findCountOfBooksByCategories();
        for (Object[] object : obj) {
            Category category = (Category) object[0];
            long bookCount = (long) object[1];
            System.out.printf("--%s: %d books%n", category.getName(), bookCount);
            Iterable<Book> books = this.bookService.findTop3ByCategoriesOrderByReleaseDate(category);
            for (Book book : books) {
                String title = book.getTitle();
                String bookYear = new SimpleDateFormat("yyyy").format(book.getReleaseDate());
                System.out.printf("%s (%s)%n", title, bookYear);
            }
        }

        //14
        String title = reader.readLine();
        Iterable<ReducedBook> booksByTitle = this.bookService.findFieldsByTitle(title);

        for (ReducedBook reducedBook : booksByTitle) {
            System.out.printf("%s - %s - %s - %s%n", reducedBook.getTitle(),
                    reducedBook.getEditionType().toString(),
                    reducedBook.getAgeRestriction().toString(),
                    reducedBook.getPrice());
        }

        //15
        String dateFormatInput = reader.readLine();
        SimpleDateFormat dFormat = new SimpleDateFormat("dd MMM yyyy");
        Date bookDate = dFormat.parse(dateFormatInput);
        int copies = Integer.parseInt(reader.readLine());

        System.out.println(this.bookService.updateByDateWithCopie(copies,bookDate));

        //16
        Integer numberOfCopies = Integer.parseInt(reader.readLine());
        System.out.printf("%d book were deleted%n",this.bookService.removeBookWithCopiesLessThan(numberOfCopies));

        //17
        String[] authorNames = reader.readLine().split("\\s+");
        System.out.println(this.authorService.getAuthorsBooksCount(authorNames[0],authorNames[1]));
    }

    private void tasksFromBookshopLab() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, 12, 31);

        //8.1
        Iterable<Book> booksAfter = this.bookService.findBooksAfter(calendar.getTime());

        for (Book book : booksAfter) {
            System.out.println(book.getTitle());
        }

        //8.2
        calendar.set(1990, 1, 1);
        Iterable<Author> authors = this.authorService.findAllWitBookBefore(calendar.getTime());

        for (Author author : authors) {
            System.out.println(author.getFirstName() + " " + author.getLastName());
        }

        //8.3
        Iterable<Author> authorsDesc = this.authorService.findAllOrderByCountOfBooks();
        for (Author author : authorsDesc) {
            System.out.println(author.getFirstName() + " " + author.getLastName());
        }

        //8.4
        Author author = this.authorService.findAuthorByLastName("Powell");
        Iterable<Book> booksByAuthor = this.bookService.findBookByAuthor(author);

        for (Book book : booksByAuthor) {
            System.out.printf("Title: %s Release date: %s Copies: %d%n", book.getTitle(), book.getReleaseDate(), book.getCopies());
        }

        //9
        List<Book> books = (List<Book>) this.bookService.findBooks();
        List<Book> threeBooks = books.subList(0, 3);

        threeBooks.get(0).getRelatedBooks().add(threeBooks.get(1));
        threeBooks.get(1).getRelatedBooks().add(threeBooks.get(0));
        threeBooks.get(0).getRelatedBooks().add(threeBooks.get(2));
        threeBooks.get(2).getRelatedBooks().add(threeBooks.get(0));

        //save related books to the database
        for (Book book : threeBooks) {
            this.bookService.save(book);
        }

        for (Book book : threeBooks) {
            System.out.printf("--%s\n", book.getTitle());
            for (Book relatedBook : book.getRelatedBooks()) {
                System.out.println(relatedBook.getTitle());
            }
        }
    }

    private void seedDatabase() throws IOException, ParseException {
        List<Author> authors = this.seedAuthors();
        List<Category> categories = this.seedCategories();
        this.seedBooks(authors, categories);

    }

    private List<Author> seedAuthors() throws IOException {
        InputStream src = getClass().getResourceAsStream("/authors.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(src));
        String line = bufferedReader.readLine();
        List<Author> authorList = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            String[] names = line.split("\\s+");
            String firstName = names[0];
            String lastName = names[1];

            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);

            authorList.add(author);
            this.authorService.save(author);
        }
        return authorList;
    }

    private List<Category> seedCategories() throws IOException {
        InputStream src = getClass().getResourceAsStream("/categories.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(src));
        String line = bufferedReader.readLine();

        List<Category> categories = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            Category category = new Category();
            category.setName(line);

            categories.add(category);
            this.categoryService.save(category);
        }

        return categories;
    }

    private void seedBooks(List<Author> authors, List<Category> categories) throws IOException, ParseException {
        InputStream src = getClass().getResourceAsStream("/books.txt");
        BufferedReader booksReader = new BufferedReader(new InputStreamReader(src));
        String line = booksReader.readLine();
        Random random = new Random();
        while ((line = booksReader.readLine()) != null) {
            String[] data = line.split("\\s+");

            int authorIndex = random.nextInt(authors.size());
            Author author = authors.get(authorIndex);
            EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            Date releaseDate = formatter.parse(data[1]);
            int copies = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < data.length; i++) {
                titleBuilder.append(data[i]).append(" ");
            }
            titleBuilder.delete(titleBuilder.lastIndexOf(" "), titleBuilder.lastIndexOf(" "));
            String title = titleBuilder.toString();

            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);

            Set<Category> booksCategory = new HashSet<>();

            for (int i = 0; i < 3; i++) {
                Category category = categories.get(random.nextInt(categories.size()));
                booksCategory.add(category);
            }

            book.setCategories(booksCategory);

            bookService.save(book);
        }

    }
}
