package BiblServiceModule;



public interface BiblServiceOperations 
{
  void addNewBook (String newISBN, String newBookName);
  void addNewReader (String bilet, String Name);
  void takeBook (String Name, String Book);
  void returnBook (String rName, String rBook);
  String getReaderInfo (String reader);
  String getFullInfo ();
} // interface BiblServiceOperations
