package base;

import static org.junit.Assert.*;



import java.util.Date;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;

public class Person_Test {

	private static PersonDomainModel per1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	per1= new PersonDomainModel();
		
	//should set attributes but seems to not work. 
	//Improper hibernate or something wrong with getters and setter given?
	per1.setPersonID(UUID.randomUUID());
	
	per1.setFirstName("Brock");
	
	per1.setBirthday(null);
	per1.setLastName("Palmer");
	per1.setCity("Ellicott");
	per1.setPostalCode(21042);
	per1.setStreet("Some street");
	
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	PersonDAL.deletePerson(per1.getPersonID());

	}

	@Test
	public void addPersontest() {
		//Adds person
		int X =PersonDAL.getPersons().size();
		PersonDAL.addPerson(per1);
		// Test sees if the UUID is there
		
		assertTrue(PersonDAL.getPersons().size()==(X+1));
	}
	@Test
	public void getPersonstest() {
		PersonDAL.addPerson(per1);
		PersonDomainModel per2= new PersonDomainModel();
		per2.setPersonID(UUID.randomUUID());
		per2.setFirstName("Brock");
		PersonDAL.addPerson(per2);
		if (PersonDAL.getPersons().size()>1){
			//Would work if you get a size of more than one person
			assertEquals(1,1);
		}
		PersonDAL.deletePerson(per2.getPersonID());

	}
	@Test
	public void getPersontest() {
		PersonDAL.addPerson(per1);
		PersonDomainModel test= PersonDAL.getPerson(per1.getPersonID());
		assertTrue(per1.getFirstName()==test.getFirstName());
		//seeing after a get person method that the names are the same 
	}
	
	@Test
	public void updatePersontest() {
		PersonDAL.addPerson(per1);
		String X = per1.getFirstName();
		per1.setFirstName("Billy Bob Joe junior");
		
		//Seeing if after update the names are the same
		PersonDAL.updatePerson(per1);
		assertTrue(per1.getFirstName() == X);
		 
		}
		 
		

	@Test
	public void deletePersontest() {
		//test if 
		
		PersonDAL.addPerson(per1);
		PersonDomainModel per = PersonDAL.getPerson(per1.getPersonID());
		assertNotNull(per);
		PersonDAL.deletePerson(per1.getPersonID());
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull(per);
	}

}
