import Planes.ExperimentalPlane;
import models.ClassificationLevel;
import models.ExperimentalTypes;
import models.MilitaryType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Planes.MilitaryPlane;
import Planes.PassengerPlane;
import Planes.Plane;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class AirportTest {

    private static final int PASSENGER_PLANES_AMOUNT = 8;
    private static final int MILITARY_PLANES_AMOUNT = 6;
    private static final int EXPERIMENTAL_PLANES_AMOUNT = 2;

    private List<Plane> planes;
    private PassengerPlane planeWithMaxPassengerCapacity;
    private Airport airport;

    @BeforeClass
    public void setUp() {
        planeWithMaxPassengerCapacity = new PassengerPlane("Boeing-747", 980, 16100, 70500, 242);

        PassengerPlane boeing737 = new PassengerPlane("Boeing-737", 900, 12000, 60500, 164);
        PassengerPlane boeing737800 = new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192);
        PassengerPlane boeing747 = new PassengerPlane("Boeing-747", 980, 16100, 70500, 242);
        PassengerPlane airbusA320 = new PassengerPlane("Airbus A320", 930, 11800, 65500, 188);
        PassengerPlane airbusA330 = new PassengerPlane("Airbus A330", 990, 14800, 80500, 222);
        PassengerPlane embraer190 = new PassengerPlane("Embraer 190", 870, 8100, 30800, 64);
        PassengerPlane superjet100 = new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140);
        PassengerPlane bombardierCS300 = new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196);
        MilitaryPlane lancer = new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER);
        MilitaryPlane spirit = new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER);
        MilitaryPlane stratofortess = new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryType.BOMBER);
        MilitaryPlane f15 = new MilitaryPlane("F-15", 1500, 12000, 10000, MilitaryType.FIGHTER);
        MilitaryPlane f22 = new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryType.FIGHTER);
        MilitaryPlane hercules = new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryType.TRANSPORT);
        ExperimentalPlane bell = new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalTypes.HIGH_ALTITUDE, ClassificationLevel.SECRET);
        ExperimentalPlane vertijet = new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ExperimentalTypes.VTOL, ClassificationLevel.TOP_SECRET);


        planes = Arrays.asList(boeing737, boeing737800, boeing747, airbusA320, airbusA330, embraer190, superjet100,
                bombardierCS300, lancer, spirit, stratofortess, f15, f22, hercules, bell, vertijet);

        airport = new Airport(planes);
    }

    @Test
    public void testGetPassengerPlanes() {
        List<PassengerPlane> passengerPlane = airport.getPassengerPlanes();

        assertEquals(PASSENGER_PLANES_AMOUNT, passengerPlane.size());
    }

    @Test
    public void testGetMilitaryPlanes() {
        List<MilitaryPlane> militaryPlanes = airport.getMilitaryPlanes();

        assertEquals(MILITARY_PLANES_AMOUNT, militaryPlanes.size());
    }

    @Test
    public void testGetExperimentalPlanes() {
        List<ExperimentalPlane> experimentalPlanes = airport.getExperimentalPlanes();

        assertEquals(EXPERIMENTAL_PLANES_AMOUNT, experimentalPlanes.size());
    }

    @Test
    public void testGetPassengerPlaneWithMaxCapacity() {
        PassengerPlane actual = airport.getPassengerPlaneWithMaxPassengersCapacity();
        PassengerPlane expected = planeWithMaxPassengerCapacity;

        assertEquals(actual, expected);
    }

    @Test
    public void testHasAtLeastOneTransportMilitaryPlane() {
        List<MilitaryPlane> transportMilitaryPlanes = airport.getTransportMilitaryPlanes();

        Assert.assertTrue(transportMilitaryPlanes.size() >= 1);
    }

    @Test
    public void testHasAtLeastOneBomberInMilitaryPlanes() {
        List<MilitaryPlane> bomberMilitaryPlanes = airport.getBomberMilitaryPlanes();

        Assert.assertTrue(bomberMilitaryPlanes.size() >= 1);
    }

    @Test
    public void testSortByMaxLoadCapacity() {
        airport.sortByMaxLoadCapacity();
        List<? extends Plane> planesSortedByMaxLoadCapacity = airport.getPlanes();
        boolean nextPlaneMaxLoadCapacityIsHigherThanCurrent = true;
        for (int i = 0; i < planesSortedByMaxLoadCapacity.size() - 1; i++) {
            Plane currentPlane = planesSortedByMaxLoadCapacity.get(i);
            Plane nextPlane = planesSortedByMaxLoadCapacity.get(i + 1);
            if (currentPlane.getMaxLoadCapacity() > nextPlane.getMaxLoadCapacity()) {
                nextPlaneMaxLoadCapacityIsHigherThanCurrent = false;
                break;
            }
        }

        Assert.assertTrue(nextPlaneMaxLoadCapacityIsHigherThanCurrent);
    }

    @Test
    public void testSortByMaxSpeed() {
        airport.sortByMaxSpeed();
        List<? extends Plane> planesSortedByMaxSpeed = airport.getPlanes();
        boolean nextPlaneMaxSpeedIsHigherThanCurrent = true;
        for (int i = 0; i < planesSortedByMaxSpeed.size() - 1; i++) {
            Plane currentPlane = planesSortedByMaxSpeed.get(i);
            Plane nextPlane = planesSortedByMaxSpeed.get(i + 1);
            if (currentPlane.getMaxSpeed() > nextPlane.getMaxSpeed()) {
                nextPlaneMaxSpeedIsHigherThanCurrent = false;
                break;
            }
        }

        Assert.assertTrue(nextPlaneMaxSpeedIsHigherThanCurrent);
    }

    @Test
    public void testSortByMaxFlightDistance() {
        airport.sortByMaxFlightDistance();
        List<? extends Plane> planesSortedByMaxDistance = airport.getPlanes();
        boolean nextPlaneMaxDistanceIsHigherThanCurrent = true;
        for (int i = 0; i < planesSortedByMaxDistance.size() - 1; i++) {
            Plane currentPlane = planesSortedByMaxDistance.get(i);
            Plane nextPlane = planesSortedByMaxDistance.get(i + 1);
            if (currentPlane.getMaxFlightDistance() > nextPlane.getMaxFlightDistance()) {
                nextPlaneMaxDistanceIsHigherThanCurrent = false;
                break;
            }
        }

        Assert.assertTrue(nextPlaneMaxDistanceIsHigherThanCurrent);
    }

}
