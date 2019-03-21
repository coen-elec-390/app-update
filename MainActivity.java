package com.example.d_gille.input_file;

import android.media.MediaRouter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public class MqttPublishSubscribeSample
    {
        public void main(String[] args)
        {
            String topic ="MQTT Examples";
            String content="Message from MqttPublishSample";
            int qos=2;
            String broker="Mosquitto";
            String clientID="Java";
            MemoryPersistence persistence=new MemoryPersistence();

            try
            {
                MqttClient sampleClient=new MqttClient(broker,clientID,persistence);
                MqttConnectOptions connOpts=new MqttConnectOptions();
                connOpts.setCleanSession(true);
                System.out.println("Connecting to broker: " + broker);
                sampleClient.connect(connOpts);
                sampleClient.subscribe('#',1);
                System.out.println("Connected");
                System.out.println("Publish Message: " _+ content);
                MqttMessage message=new MqttMessage(content.getBytes());
                message.setQos(qos);
                sampleClient.setCallback(new MediaRouter.SimpleCallback());
                sampleClient.publish(topic,message);
                System.out.println("Message published");
                try
                {
                    Thread.sleep(5000);
                    sampleClient.disconnect();
                }

                catch(Exception e)
                {
                    e.printStackTrace();

                }

                System.out.println("Disconnected");
                System.exit(0);
            }

            catch(MqttException me)
            {
                System.out.println("reason " + me.getReasonCode());
                System.out.println("msg" + me.getMessage());
                System.out.println("loc" +me.getLocalizedMessage());
                System.out.println("cause " +me.getCause());
                System.out.println("except " + me);
                me.printStackTrace();
            }
        }

    }
}
