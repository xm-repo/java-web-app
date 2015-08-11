

-- -----------------------------------------------------
-- Table `PropertyStructure`.`PhysicalPerson`
-- -----------------------------------------------------

DELETE `PropertyStructure`.`PhysicalPerson`.* FROM `PropertyStructure`.`PhysicalPerson`; 

INSERT INTO `PropertyStructure`.`PhysicalPerson` (`id`, `FullName`, `Contacts`, `Biography`) VALUES 
(1, 'Joseph Addison', 'Milston, Wiltshire', 'Joseph Addison (1 May 1672 – 17 June 1719) was an English essayist, poet, playwright, and politician.'),
(2, 'Nicholas Amhurst', 'Marden, Kent', 'Nicholas Amhurst (16 October 1697 – 27 April 1742) was an English poet and political writer.'),
(3, 'Jane Barker', 'Blatherwick, Northamptonshire', 'Jane Barker (1652–1732) was an English poet and novelist of the early 18th century.'),
(4, 'Isaac Chauncy', 'Ware, Hertfordshire', 'Isaac Chauncy (1632–1712) was an English dissenting minister.'),
(5, 'John Bancks', ' Sonning, Berkshire', 'John Bancks (1709 – 19 April 1751), also known as John Banks, was an English writer.'),
(6, 'William Derham', 'Stoulton, Worcestershire', 'William Derham FRS (26 November 1657 – 5 April 1735) was an English clergyman and natural philosopher. 
    He produced the earliest, reasonably accurate estimate of the speed of sound.'),
(7, 'Eliza Berkeley', 'White Waltham, Windsor Forest', 'Eliza Berkeley (née Frinsham; 1734–1800) was an English author.'),
(8, 'William Blake', 'Soho, London', 'William Blake (28 November 1757 – 12 August 1827) was an English poet, painter and printmaker.'),
(9, 'Henry Fielding', 'Sharpham, Glastonbury, Somerset', 'Henry Fielding (22 April 1707 – 8 October 1754) was an English novelist and dramatist 
    known for his rich earthy humour and satirical prowess, and as the author of the novel Tom Jones.'),
(10, 'Henry Gally', 'Beckenham, Kent', 'Henry Gally, D.D. (1696-1769) was an English divine and classical scholar.');


-- -----------------------------------------------------
-- Table `PropertyStructure`.`LegalEntity`
-- -----------------------------------------------------

DELETE `PropertyStructure`.`LegalEntity`.* FROM `PropertyStructure`.`LegalEntity`;

INSERT INTO `PropertyStructure`.`LegalEntity` (`id`, `BusinessName`, `IsLe`, `FoundationDate`, `Miscellaneous`) VALUES
(1, '3M', TRUE, '1976-09-08', 'Conglomerate.'), 
(2, 'American Express', TRUE, '1982-08-30', 'Consumer finance.'),
(3, 'AT&T', TRUE, '1999-11-01', 'Telecommunications.'),
(4, 'Boeing', TRUE, '1987-12-03', 'Aerospace and defense.'),
(5, 'Caterpillar', TRUE, '1991-05-06', 'Construction and mining equipment.'),
(6, 'Chevron', TRUE, '2008-02-19', 'Oil & gas.'),
(7, 'Cisco Systems', TRUE, '2009-06-08', 'Computer networking.'),
(8, 'Coca-Cola', TRUE, '1987-03-12', 'Beverages.'),
(9, 'DuPont', TRUE, '1935-11-20', 'Chemical industry.'),
(10, 'General Electric', FALSE, '1907-11-07', 'Conglomerate.'),
(11, 'Goldman Sachs', FALSE, '2013-09-20', 'Banking, Financial services.'),
(12, 'The Home Depot', FALSE, '1999-11-01', 'Home improvement retailer.'),
(13, 'Intel', FALSE, '1999-11-01', 'Semiconductors.'),
(14, 'IBM', FALSE, '1979-06-29', 'Computers and technology.'),
(15, 'ExxonMobil', FALSE, '1928-10-01', 'Oil & gas');


-- -----------------------------------------------------
-- Table `PropertyStructure`.`Owner`
-- -----------------------------------------------------

DELETE `PropertyStructure`.`Owner`.* FROM `PropertyStructure`.`Owner`;

INSERT INTO `PropertyStructure`.`Owner` (`id`, `PhPersonOwnerId`, `LeOwnerId`, `LeId`, `Property`) VALUES
(1, 1, NULL, 1, 40),
(2, 2, NULL, 1, 50),
(3, 1, NULL, 2, 20),
(4, 2, NULL, 2, 20),
(5, 3, NULL, 2, 20),
(6, 4, NULL, 2, 40),
(7, 5, NULL, 3, 100),
(8, 5, NULL, 4, 100),
(9, 5, NULL, 5, 100),
(10, 5, NULL, 6, 10),
(11, NULL, 1, 6, 20),
(12, NULL, 2, 6, 20),
(13, NULL, 3, 6, 20),
(14, NULL, 4, 6, 10),
(15, NULL, 5, 6, 20),
(16, NULL, 7, 8, 50),
(17, NULL, 9, 8, 50),
(18, NULL, 10, 11, 90),
(19, NULL, 10, 1, 10),
(20, NULL, 12, 11, 10);


-- -----------------------------------------------------
-- Table `PropertyStructure`.`History`
-- -----------------------------------------------------

DELETE `PropertyStructure`.`History`.* FROM `PropertyStructure`.`History`;

INSERT INTO `PropertyStructure`.`History` (`id`, `ChangeDate`, `OldBusinessName`, `Miscellaneous`, `LeId`) VALUES
(1, '1999-04-05', 'Walt Disney Co', '', 15),
(2, '1998-05-06', 'Wal-Mart Stores', '', 15),
(3, '1997-01-02', 'Verizon Communications', '', 15),
(4, '1996-02-03', 'United Technologies Corp', '', 15),
(5, '1995-03-04', 'Procter & Gamble Co', '', 15);


-- -----------------------------------------------------
-- Table `PropertyStructure`.`Business`
-- -----------------------------------------------------

DELETE `PropertyStructure`.`Business`.* FROM `PropertyStructure`.`Business`;

#INSERT INTO `PropertyStructure`.`Business` (`id`, `SubjectLeId`, `ObjectLeId`, `PurchaseDate`, `Miscellaneous`) VALUES
#(1, 15, 14, '2010-01-02', 'day 1'),
#(2, 15, 13, '2010-01-02', 'day 2'),
#(3, 15, 12, '2010-01-02', 'day 3'),
#(4, 15, 11, '2011-02-03', 'another day'),
#(5, 11, 10, '2014-03-10', 'business');
    
    

