package service;

import model.Person;
import model.PersonDTO;

import javax.naming.*;
import javax.naming.directory.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class PersonDAO implements IPersonService {
    private final String base = "CN=Users,DC=test,DC=hivetech,DC=vn";
    private static final String factory = "com.sun.jndi.ldap.LdapCtxFactory";
    private static final String url = "LDAP://192.168.1.148:389";
    private static final String username = "CN=Ldap Modify Pwd,CN=Users,DC=test,DC=hivetech,DC=vn";
    private static final String password = "2r4w3esfD";
    public static final String[] OBJECT_CLASSES = new String[]{"top", "person", "organizationalPerson", "user"};

    public static DirContext connect() throws NamingException {
        Hashtable<String, String> ldap = new Hashtable<>();
        ldap.put(Context.INITIAL_CONTEXT_FACTORY, factory);
        ldap.put(Context.PROVIDER_URL,url);
        ldap.put(Context.SECURITY_AUTHENTICATION, "simple");
        ldap.put(Context.SECURITY_PRINCIPAL, username);
        ldap.put(Context.SECURITY_CREDENTIALS, password);
        return new InitialDirContext(ldap);
    }



    @Override
    public void getAllPerson() throws NamingException {

        DirContext context = connect();

        SearchControls searchControls = new SearchControls();

        String[] Attrs={"distinguishedName", "givenName", "sAMAccountName"};

        //Specify the search scope
        searchControls.setReturningAttributes(Attrs);

        //specify the LDAP search filter person where sAMAccountName contains vinhnq
        String searchFilter = "(&(objectClass=User)(sAMAccountName=vinhnq*))";

        //Specify the Base for the search
        String searchBase = base;



        // Search for objects using the filter
        NamingEnumeration<SearchResult> answer = context.search(searchBase, searchFilter, searchControls);
        List<Person> personList = new ArrayList<>();
        //Loop through the search results
        while (answer.hasMoreElements()) {
            SearchResult sr = answer.next();
            Attributes attrs = sr.getAttributes();
            Person person = new Person();
            person.setSAMAccountName(attrs.get("sAMAccountName").get().toString());
            person.setDistinguishedName(attrs.get("distinguishedName").get().toString());
            person.setGivenName(String.valueOf(attrs.get("givenName")));
            personList.add(person);
            System.out.println(person);
        }
        context.close();
    }

    @Override
    public void createPerson(Person person) throws NamingException {
        DirContext context = connect();
        Attributes attrs = new BasicAttributes(true);
        Attribute objclass = new BasicAttribute("objectclass");
        PersonDTO personDTO = new PersonDTO(person.getSAMAccountName(),person.getGivenName());
        objclass.add("top");
        objclass.add("person");
        objclass.add("organizationalPerson");
        objclass.add("user");
        Attribute sAMAccountName = new BasicAttribute("sAMAccountName", person.getSAMAccountName());
        Attribute givenName = new BasicAttribute("givenName", person.getGivenName());
        Attribute sn = new BasicAttribute("sn", "Abc");
        Attribute password = new BasicAttribute("password", "1e3q2wadS");

        attrs.put(objclass);
        attrs.put(givenName);
        attrs.put(sAMAccountName);
        attrs.put(sn);
        attrs.put(password);

        try {
            context.createSubcontext(person.getDistinguishedName(), attrs);
            System.out.println("success");
//            context.bind(person.getDistinguishedName(),personDTO , attrs);
        } catch (NamingException e) {
            System.out.println("false");
            e.printStackTrace();
        }
        context.close();
    }

    @Override
    public void deletePerson(String distinguishedName) throws NamingException {
        DirContext context = connect();
        try {
            context.unbind(distinguishedName);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        context.close();
    }

    @Override
    public void updatePerson(Person person) throws NamingException {
        DirContext context = connect();
        Attributes attrs = new BasicAttributes(true);
        Attribute objclass = new BasicAttribute("objectclass");
        objclass.add("top");
        objclass.add("person");
        objclass.add("organizationalPerson");
        objclass.add("user");
        Attribute sAMAccountName = new BasicAttribute("sAMAccountName", person.getSAMAccountName());
        Attribute givenName = new BasicAttribute("givenName", person.getGivenName());
//        Attribute sn = new BasicAttribute("sn", "Abc");
//        attrs.put(objclass);
        attrs.put(givenName);
        attrs.put(sAMAccountName);
//        attrs.put(sn);

        try{
            context.modifyAttributes(person.getDistinguishedName(), DirContext.REPLACE_ATTRIBUTE, attrs);
//            context.rebind(person.getDistinguishedName(), DirContext.REPLACE_ATTRIBUTE, attrs);
            System.out.println("success");
        } catch (NamingException e) {
            System.out.println("fail");
            e.printStackTrace();
        }
    }
}
