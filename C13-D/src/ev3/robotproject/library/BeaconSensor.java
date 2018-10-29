package ev3.robotproject.library;

import java.io.IOException;

import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class BeaconSensor {

	final static EV3IRSensor ir = new EV3IRSensor(SensorPort.S4);
	static SampleProvider seek = ir.getSeekMode();

	public BeaconSensor(Port port) {
		// dit is een utility class en gebruikt dus geen constructor.
	}

	public static float getDirection() {
		// methode die de richting opvraagt van de beacon
		float[] sample = new float[seek.sampleSize()];

		seek.fetchSample(sample, 0);
		return sample[0];
	}

	public static float getDistance() {
		// methode die de afstand opvraagt van de beacon
		float[] sample = new float[seek.sampleSize()];

		seek.fetchSample(sample, 0);
		return sample[1];
	}

	public static void sluit() {
		// sluiten van de BeaconSensor (free up resources).
		ir.close();
	}

	public static void zoekBeacon() {
		// Methode die de robot laat draaien totdat de beacon voor hem ligt.
		do {
			Lcd.print(1, "BeaconZoeker actief");
			Lcd.clear(2);
			Lcd.clear(3);
			Lcd.clear(4);
			Lcd.print(5, "Versie 1.0.5");
			Lcd.print(6, "Dir: %f", getDirection());
			Lcd.print(7, "Dis: %f", getDistance());
			Motor.draaiOmAs(100, -100);
			Delay.msDelay(5000);
		} while (getDirection() > 10 || getDirection() < -10);

		Motor.rem();
	}

}
