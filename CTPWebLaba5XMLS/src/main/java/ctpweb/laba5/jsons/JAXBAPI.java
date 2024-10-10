package ctpweb.laba5.jsons;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.hibernate.Session;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ctpweb.laba5.jsons.model.Person;
import ctpweb.laba5.jsons.model.PersonXML;

public class JAXBAPI {
	public static Person executeXML(String filename, Session session) throws IOException, JAXBException {
        PersonXML personXML = getQuery(filename);
        switch (personXML.getQtype()) {
            case CREATE:
                create(personXML.getEntity(), session);
                break;
            case UPDATE:
                update(personXML.getEntity(), session);
                break;
            case READ:
                return read(personXML.getEntity(), session);
            case DELETE:
                delete(personXML.getEntity(), session);
                break;
        }
        return null;
    }

    public static PersonXML getQuery(String filename) throws IOException, JAXBException {
        StringBuilder strbldr = new StringBuilder();
        ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
        for (String line : lines) {
            strbldr.append(line);
        }
        JAXBContext jaxbContext = JAXBContext.newInstance(PersonXML.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (PersonXML) jaxbUnmarshaller.unmarshal(new StringReader(strbldr.toString()));
    }

    private static void create(Person entity, Session session) {
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
    }

    private static void update(Person entity, Session session) {
        session.beginTransaction();
        Person mod = session.get(Person.class, entity.getId());
        if (entity.getSurname() != null) {
            mod.setSurname(entity.getSurname());
        }
        if (entity.getAddress() != null) {
            mod.setAddress(entity.getAddress());
        }
        mod.setDutyBound(entity.getDutyBound());
        session.update(mod);
        session.getTransaction().commit();
    }

    private static Person read(Person entity, Session session) {
        return session.get(Person.class, entity.getId());
    }

    private static void delete(Person entity, Session session) {
        session.beginTransaction();
        Person myObject = session.load(Person.class, entity.getId());
        session.delete(myObject);
        session.getTransaction().commit();
    }

    public static void addFromJson(@SuppressWarnings("SameParameterValue") String filename, Session session) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd").create();
        try (Reader reader = new FileReader(filename)) {
            Person personEntity = gson.fromJson(reader, Person.class);
            create(personEntity, session);
        }
    }

    public static void dumpToJson(@SuppressWarnings("SameParameterValue") String filename, Session session, @SuppressWarnings("SameParameterValue") String ID) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Person personEntity = session.find(Person.class, ID);
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(personEntity, writer);
        }
    }
}
