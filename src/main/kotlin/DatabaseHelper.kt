class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "mydatabase.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Tabelle für Produkte erstellen
        db.execSQL(ProductTable.CREATE_TABLE_QUERY)

        // Weitere Initialisierungen oder Beispieldaten einfügen...
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Hier könntest du Upgrades der Datenbankstruktur durchführen
    }
}
