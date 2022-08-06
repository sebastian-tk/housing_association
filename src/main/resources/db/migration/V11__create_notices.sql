CREATE TABLE IF NOT EXISTS notices (
    id INTEGER PRIMARY KEY AUTO_INCREMENT UNIQUE ,
    title VARCHAR(60) NOT NULL,
    description TEXT NOT NULL,
    execution_date DATE,
    archived BIT DEFAULT 0,
    building_id INTEGER,
    CONSTRAINT FK_NoticeBuilding FOREIGN KEY (building_id) REFERENCES buildings (id) ON DELETE CASCADE
);

ALTER TABLE notices ADD CONSTRAINT uniqueFields UNIQUE (title, building_id,execution_date);

INSERT INTO notices(title, description,execution_date, building_id)
VALUES
    ("Koszenie trawy, żywopłotu","Zarzad spółdzielni mieszkaniowej informuje że z w dniu 6 sierpnia 2022 bedzie realizowane koszenie trawy i przycinanie żywopłotów. Mieszkancy sa proszeni aby w tym czasie w miare możliwosci nie korzystac z trawnika.","2022-08-03",1),
    ("Koszenie trawy","Zarzad spółdzielni mieszkaniowej informuje że z w dniu 30 sierpnia 2022 bedzie realizowane koszenie trawy. Mieszkancy sa proszeni aby w tym czasie w miare możliwosci nie korzystac z trawnika.","2022-08-30",3),
    ("Wymiana rury ciepłowniczej","Zarzad spółdzielni mieszkaniowej informuje że z w dniu 16 sierpnia 2022 bedzie realizowana wymiana rury ciepłowniczej ktory rozmieszczona jest wzdłuż ulicy Wólcznskiej sąsiadujac z blokiem Jarmuz. W tym dniu prosimy o nieparkowanie samochodow wzdłuż chodnika ulicy Wólczanskiej oraz nie korzystanie z frontowego parkingu wspólnoty. Za wszelkie uniedogodnienia przepraszamy.","2022-08-16",5),
    ("Remont elewacji","Zarzad spółdzielni mieszkaniowej informuje że z w dniu 20 wrzesnia 2022 rozpoczyna remont elewacji sciany zachodniej budynku. Mieszkancy sa proszeni aby nie parkowac aut w czasie remontu przy danej czesci budynku w godzinach 8.00 -18.00. Alternatywnie mozna korzystac z parkingu marketu Lidl tylko w okresie remontu.","2022-08-20",3),
    ("Wymiana licznikow energii elektrycznej","Zarzad spółdzielni mieszkaniowej informuje że w dniu 10 sierpnia nastapi wymiana licznikow energi elktrycznej dla klatki schodowej I w godzinach 16-20 godz. Mieszkancow prosimy o umozliwienie wykonawcy przeprowadzenia wymiany poprzez obecnosc w tym dniu w mieszkaniu badz skontaktowanie sie z wykonawca pod umerem telefonu 890-782-123 w celu zmiany terminu.","2022-08-20",2),
    ("Wymiana drzwi frontowych","Zarzad spółdzielni mieszkaniowej informuje że w dniu 6 wrzesnia 2022 roku, firma MatBud przeprowadzi wymiane drzwi frontowych w godzinach porannych. Korzystanie w klatki schodowej w tym dniu moze byc znacznie utrudnione. Dodatkowo osoby zostawiajace rowery i inne pojazdy ruchome w obrebie klatki, proszone sa aby w tym dniu nie korzystaly z klatki schodowej","2022-08-2",5),
    ("Malowanie klatki schodowej","Zarzad spółdzielni mieszkaniowej informuje że w dniu 23 sierpnia 2022 roku zostanie rozpoczety remont obejmujacy malownaie klatki schodowej. Osoby przetrzymujace rowery, wózki , hulajnogi itp proszeni sa aby w tym dniu i najblizszych nie korzystaly z klatki schodowej . Utrudni to przeprowadzenie remontu a w konsekwensji moze doprowadzic do przedluzenia czasu trwania remontu","2022-08-23",1),
    ("Malowanie klatki schodowej","Zarzad spółdzielni mieszkaniowej informuje że w dniu 9 sierpnia 2022 roku zostanie rozpoczety remont obejmujacy malownaie klatki schodowej. Osoby przetrzymujace rowery, wózki , hulajnogi itp proszeni sa aby w tym dniu i najblizszych nie korzystaly z klatki schodowej . Utrudni to przeprowadzenie remontu a w konsekwensji moze doprowadzic do przedluzenia czasu trwania remontu","2022-08-09",5),
    ("Przeglad wentylacji","Zarzad spółdzielni mieszkaniowej informuje że w dniu 29 sierpnia 2022 roku zostanie przeprowadzony przeglad instalacji wentylacyjnych w godzinach popoludniowych. Mieszkancow prosimy o przebywanie w mieszkaniach w tym okresie w celu umozliwienia przeprowadzenia przegladu.","2022-08-29",5),
    ("Przeglad instalacji gazowych","Zarzad spółdzielni mieszkaniowej informuje że w dniu 13 wrzesnia 2022 roku zostanie przeprowadzone  przeglad instalacji gazowych w godzinach popoludniowych. Mieszkancow prosimy o przebywanie w mieszkaniach w tym okresie w celu umozliwienia przeprowadzenia przegladu.","2022-09-13",3),
    ("Przeglad instalacji gazowych","Zarzad spółdzielni mieszkaniowej informuje że w dniu 23 wrzesnia 2022 roku zostanie przeprowadzone  przeglad instalacji gazowych w godzinach 15.00-20.00godz. Mieszkancow prosimy o przebywanie w mieszkaniach w tym okresie w celu umozliwienia przeprowadzenia przegladu.","2022-09-23",4),
    ("Przeglad instalacji gazowych","Zarzad spółdzielni mieszkaniowej informuje że w dniu 29 wrzesnia 2022 roku zostanie przeprowadzone  przeglad instalacji gazowych w godzinach 16.00-20.00godz. Mieszkancow prosimy o przebywanie w mieszkaniach w tym okresie w celu umozliwienia przeprowadzenia przegladu.","2022-09-29",5),
    ("Przebudowa parkingu","Zarzad spółdzielni mieszkaniowej informuje że w dniu 3 wrzesnia 2022 roku rozpoczeta przebudowa parkingu przy ulicy Gdanskiej od strony zachodniej budynku. Przebudowa bedzie obejmowac wymiane nawierzchni parkingu jak i powiekszenie w kierunku wschodnim. Wszystkie osoby parkujace proszone sa o powstryzmanie sie od prakowania w tej okolicy w okresie remontu","2022-09-03",1);
