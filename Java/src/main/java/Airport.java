import Planes.ExperimentalPlane;
import models.MilitaryType;
import Planes.MilitaryPlane;
import Planes.PassengerPlane;
import Planes.Plane;

import java.util.*;

import static models.MilitaryType.BOMBER;
import static models.MilitaryType.TRANSPORT;

public class Airport {
    private List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }

    public List<PassengerPlane> getPassengerPlanes() {
        List<PassengerPlane> passengerPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane instanceof PassengerPlane) {
                passengerPlanes.add((PassengerPlane) plane);
            }
        }
        return passengerPlanes;
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        List<MilitaryPlane> militaryPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane instanceof MilitaryPlane) {
                militaryPlanes.add((MilitaryPlane) plane);
            }
        }
        return militaryPlanes;
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        List<ExperimentalPlane> experimentalPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane instanceof ExperimentalPlane) {
                experimentalPlanes.add((ExperimentalPlane) plane);
            }
        }
        return experimentalPlanes;
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        List<PassengerPlane> passengerPlanes = getPassengerPlanes();
        PassengerPlane planeWithMaxCapacity = passengerPlanes.get(0);
        for (PassengerPlane passengerPlane : passengerPlanes) {
            int currentCapacity = passengerPlane.getPassengersCapacity();
            int maxCapacity = planeWithMaxCapacity.getPassengersCapacity();
            if (currentCapacity > maxCapacity) {
                planeWithMaxCapacity = passengerPlane;
            }
        }
        return planeWithMaxCapacity;
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
        return getMilitaryPlaneByType(militaryPlanes, TRANSPORT);
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
        return getMilitaryPlaneByType(militaryPlanes, BOMBER);

    }

    public void sortByMaxFlightDistance() {
        planes.sort(Comparator.comparingInt(Plane::getMaxFlightDistance));
    }

    public void sortByMaxSpeed() {
        planes.sort(Comparator.comparingInt(Plane::getMaxSpeed));
    }

    public void sortByMaxLoadCapacity() {
        planes.sort(Comparator.comparingInt(Plane::getMaxLoadCapacity));
    }

    private List<MilitaryPlane> getMilitaryPlaneByType(List<MilitaryPlane> militaryPlanes, MilitaryType militaryType) {
        List<MilitaryPlane> militaryPlanesByType = new ArrayList<>();
        for (MilitaryPlane plane : militaryPlanes) {
            boolean isMilitaryTransportPlane = plane.getType().equals(militaryType);
            if (isMilitaryTransportPlane) {
                militaryPlanesByType.add(plane);
            }
        }
        return militaryPlanesByType;
    }

    public List<? extends Plane> getPlanes() {
        return planes;
    }

    @Override
    public String toString() {
        return "Airport{" + "Planes=" + planes.toString() + '}';
    }
}
