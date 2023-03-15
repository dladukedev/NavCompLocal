package com.dladukedev.navigationlocal

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

data class Book(
    val id: Int,
    val title: String,
    val description: String? = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
    val isFavorite: Boolean = false,
)

object BookRepository {
    private val books = MutableStateFlow(dummyBooks)

    fun toggleFavorite(bookId: Int) {
        books.update { current ->
            current.map { if (it.id == bookId) it.copy(isFavorite = !it.isFavorite) else it }
        }
    }

    fun readBook(bookId: Int?): Flow<Book?> {
        return books.map { allBooks ->
            allBooks.firstOrNull { book -> book.id == bookId }
        }
    }

    fun browseBooks(): Flow<List<Book>> {
        return books
    }

    fun browseFavoriteBooks(): Flow<List<Book>> {
        return books.map { allBooks ->
            allBooks.filter { it.isFavorite }
        }
    }

    fun browseNonFavoriteBooks(): Flow<List<Book>> {
        return books.map { allBooks ->
            allBooks.filterNot { it.isFavorite }
        }
    }
}

private val dummyBooks = listOf(
    Book(12648793, "Pride and Prejudice"),
    Book(12707846, "Bible"),
    Book(10527843, "Alice's Adventures in Wonderland"),
    Book(8157718, "Adventures of Huckleberry Finn"),
    Book(8229197, "Treasure Island"),
    Book(13301713, "A Tale of Two Cities"),
    Book(9278292, "Sense and Sensibility"),
    Book(13299222, "A Christmas Carol"),
    Book(6795866, "Little Women"),
    Book(13300802, "Oliver Twist"),
    Book(10223922, "The Hound of the Baskervilles"),
    Book(10934305, "El ingenioso hidalgo Don Quijote de la Mancha"),
    Book(13322313, "Great Expectations"),
    Book(6717853, "The Adventures of Sherlock Holmes [12 stories]"),
    Book(12648655, "The Wonderful Wizard of Oz"),
    Book(8257991, "Romeo and Juliet"),
    Book(7424001, "Macbeth"),
    Book(12736044, "Candide"),
    Book(8236937, "The Last of the Mohicans"),
    Book(7103517, "The Adventures of Tom Sawyer"),
    Book(12155935, "Scarlet Letter"),
    Book(7182819, "The Merchant of Venice"),
    Book(12993424, "Madame Bovary"),
    Book(7901303, "Julius Caesar"),
    Book(13115624, "David Copperfield"),
    Book(12726168, "Il principe"),
    Book(12728198, "Uncle Tom's Cabin"),
    Book(28174, "Five Children and It"),
    Book(5654516, "The Scarlet Letter"),
    Book(479102, "The Scarlet Pimpernel"),
    Book(13136548, "Ulysses"),
    Book(5601773, "Divina Commedia"),
    Book(12997559, "Moll Flanders"),
    Book(6657951, "Ab urbe condita"),
    Book(5720552, "Book of common prayer"),
    Book(8783768, "Robinson Crusoe"),
    Book(10852435, "Anne of Green Gables"),
    Book(13241123, "The Railway Children"),
    Book(12721865, "Les Misérables"),
    Book(13119794, "The Old Curiosity Shop"),
    Book(7420215, "King Henry V"),
    Book(8236400, "The Call of the Wild"),
    Book(8237593, "The deerslayer"),
    Book(
        13241364,
        "The Story of the Treasure Seekers being the adventures of the Bastable children in search of a fortune"
    ),
    Book(6073528, "Decamerone"),
    Book(7152019, "The Beautiful and the Damned"),
    Book(8235363, "Jane Eyre"),
    Book(9000447, "Leaves of Grass"),
    Book(11048623, "Manifest der Kommunistischen Partei"),
    Book(6968564, "Aeneis"),
    Book(12332709, "A la recherche du temps perdu"),
    Book(12968864, "The History of Tom Jones"),
    Book(116833, "King Henry IV. Part 1"),
    Book(8980913, "The Pathfinder"),
    Book(8237041, "The Moonstone"),
    Book(8222096, "Antony and Cleopatra"),
    Book(566208, "On the Nature of the Universe"),
    Book(9168732, "A Connecticut Yankee in King Arthur's Court"),
    Book(9020720, "King Richard III"),
    Book(5578106, "The Prairie: A Tale"),
    Book(2560652, "Анна Каренина"),
    Book(9000512, "The pioneers"),
    Book(8595966, "Alice's Adventures in Wonderland / Through the Looking Glass"),
    Book(8236920, "White Fang"),
    Book(9284872, "The uncommercial traveller"),
    Book(7889534, "Taming of the Shrew"),
    Book(8231790, "The Jungle"),
    Book(9069548, "King Richard II"),
    Book(8241487, "The House of the Seven Gables"),
    Book(5647361, "Autobiography"),
    Book(12973053, "Jude the Obscure"),
    Book(502823, "The Elusive Pimpernel (Scarlet Pimpernel)"),
    Book(13154732, "The dragon and the raven"),
    Book(9025496, "The Secret Garden"),
    Book(8222112, "Coriolanus"),
    Book(1053011, "Kim"),
    Book(9283961, "Barnaby Rudge"),
    Book(6060585, "Satyricon"),
    Book(10679669, "Lives"),
    Book(8236320, "Cyrano de Bergerac"),
    Book(8272336, "Бра́тья Карама́зовы"),
    Book(8236285, "Война и мир"),
    Book(6644514, "The Enchanted Castle"),
    Book(8237948, "Anne of Avonlea"),
    Book(12593945, "Nicomachean Ethics"),
    Book(2780742, "At The Back Of The North Wind"),
    Book(9286405, "Silas Marner"),
    Book(12947584, "Memoirs of Fanny Hill"),
    Book(6366973, "History"),
    Book(8350410, "The Case-Book of Sherlock Holmes"),
    Book(7339581, "King Henry IV. Part 2"),
    Book(902122, "The Phoenix and the Carpet"),
    Book(9814431, "L'Étranger"),
    Book(12726959, "Rights of Man"),
    Book(8221267, "The Prince and the Pauper"),
    Book(4684227, "The Woman in White"),
    Book(8236915, "The Red Badge of Courage"),
    Book(8584021, "Het Achterhuis"),
    Book(104355, "Le comte de Monte-Cristo"),
    Book(9023411, "Plays (36)")
)