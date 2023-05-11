import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
class PhonebookApplication {
    private Map<String, Contact> contacts;
    private static final String DATA_FILE = "contacts.dat";

    public PhonebookApplication() {
        contacts = new TreeMap<>();
    }

    private void addContact(Contact contact) {
        contacts.put(contact.getName(), contact);
        System.out.println("Contact added successfully!");
    }

    private Contact searchContact(String name) {
        return contacts.get(name);
    }

    private void updateContact(String name) {
        Contact contact = searchContact(name);
        if (contact != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter new name:");
            String newName = scanner.nextLine();
            System.out.println("Enter new phone number:");
            String newPhoneNumber = scanner.nextLine();
            System.out.println("Enter new email:");
            String newEmail = scanner.nextLine();

            contact.setName(newName);
            contact.setPhoneNumber(newPhoneNumber);
            contact.setEmail(newEmail);

            System.out.println("Contact updated successfully!");
        } else {
            System.out.println("Contact not found!");
        }
    }

    private void deleteContact(String name) {
        Contact contact = searchContact(name);
        if (contact != null) {
            contacts.remove(name);
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Contact not found!");
        }
    }

    private void displayContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found!");
            return;
        }

        System.out.println("----- Contacts -----");
        for (Contact contact : contacts.values()) {
            System.out.println(contact);
        }
    }

    private void saveContacts() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(DATA_FILE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(contacts);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Contacts saved successfully!");
        } catch (IOException e) {
            System.out.println("Failed to save contacts: " + e.getMessage());
        }
    }

    private void loadContacts() {
        try {
            File file = new File(DATA_FILE);
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                contacts = (Map<String, Contact>) objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();
                System.out.println("Contacts loaded successfully!");
            } else {
                contacts = new TreeMap<>();
                System.out.println("New contacts file created.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to load contacts: " + e.getMessage());
        }
    }
    

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("----- Phonebook Application -----");
            System.out.println("1. Add Contact");
            System.out.println("2. Search Contact");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Display Contacts");
            System.out.println("6. Save Contacts");
            System.out.println("7. Load Contacts");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    Contact newContact = new Contact(name, phoneNumber, email);
                    addContact(newContact);
                    break;
                case 2:
                    System.out.print("Enter name to search: ");
                    String searchName = scanner.nextLine();
                    Contact foundContact = searchContact(searchName);
                    if (foundContact != null) {
                        System.out.println("Contact found:");
                        System.out.println(foundContact);
                    } else {
                        System.out.println("Contact not found!");
                    }
                    break;
                case 3:
                    System.out.print("Enter name to update: ");
                    String updateName = scanner.nextLine();
                    updateContact(updateName);
                    break;
                case 4:
                    System.out.print("Enter name to delete: ");
                    String deleteName = scanner.nextLine();
                    deleteContact(deleteName);
                    break;
                case 5:
                    displayContacts();
                    break;
                case 6:
                    saveContacts();
                    break;
                case 7:
                    loadContacts();
                    break;
                case 8:
                    System.out.println("Exiting Phonebook Application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        PhonebookApplication phonebookApp = new PhonebookApplication();
        phonebookApp.run();
    }
}


