import java.util.*;
import java.io.*;

class FlightManagement{
public static void main(String[] args)
{
    Scanner sc = new Scanner(System.in);
    System.out.println("Choose an option: 1.add 2.delete 3.update");
    int choice = sc.nextInt();
    sc.nextLine();
    switch(choice)
    {
        case 1:
        System.out.println("Enter the flight details: ");
        String n=sc.nextLine();
        String s=sc.nextLine();
        String d=sc.nextLine();
        int dist=sc.nextInt();
        int no=sc.nextInt();
        System.out.println("Is the flight available? ");
        boolean op=sc.nextBoolean();
        FlightData f1 = new FlightData(n,s,d,dist,no,op);
        f1.addFlightData();
        break;

        case 2:
        System.out.println("Enter the flight name: ");
        String n2 = sc.nextLine();
        FlightData f2 = new FlightData(n2);
        f2.deleteFlightData();
        break;

        case 3:
        System.out.println("Enter the flight name: ");
        String n3 = sc.nextLine();
        FlightData f3 = new FlightData(n3);
        System.out.println("Enter the new flight details: ");
        String n4=sc.nextLine();
        String s4=sc.nextLine();
        String d4=sc.nextLine();
        int dist4=sc.nextInt();
        int no4=sc.nextInt();
        System.out.println("Is the flight available? ");
        boolean op4=sc.nextBoolean();
        FlightData f4 = new FlightData(n4,s4,d4,dist4,no4,op4);
        f3.updateFlightData(f4);
        break;

    }
    sc.close();
}
}


class FlightData
{
    
    String name;
    String source;
    String destination;
    int distance;
    int seats;
    boolean availability;

    public FlightData(String n)
    {
        name=n;
    }
    public FlightData(String n,String s,String d,int dist,int seat,boolean t)
    {
        name=n;
        source=s;
        destination=d;
        distance=dist;
        seats=seat;
        availability=t;
    }
    public void addFlightData()
    {
        try(BufferedWriter ob = new BufferedWriter(new FileWriter("flightdata.txt", true));)
        {
        ob.write(name+" ");
        ob.write(source+" ");
        ob.write(destination+" ");
        ob.write(distance+" ");
        ob.write(seats+" ");
        ob.write(availability+" ");
        ob.newLine();
        ob.close();
        }
        catch(IOException e)
        {
            System.out.println("Error");
        }
    }
    public void deleteFlightData()
    {
        List<String> list = new ArrayList<>();
        File inputFile = new File("flightdata.txt");
        File tempFile = new File("flightdata_temp.txt");
        try(BufferedReader oldfile = new BufferedReader(new FileReader(inputFile));
        BufferedWriter newfile = new BufferedWriter(new FileWriter(tempFile));)
        {
        String line;
        while((line = oldfile.readLine())!=null)
        {
            if(!line.contains(name))
            {
                list.add(line);
            }
        }
        for(String s:list)
        {
            newfile.write(s);
            newfile.newLine();
            
        }
    }
    catch(IOException e)
    {
        System.out.println("Error");
    }
    if (!inputFile.delete()) {
        System.out.println("Could not delete original file");
        return;
    }
    if (!tempFile.renameTo(inputFile)) {
        System.out.println("Could not rename temp file");
    }
    }
    public void updateFlightData(FlightData newdata)
    {
        List<String> list = new ArrayList<>();
        File inputFile = new File("flightdata.txt");
        File tempFile = new File("flightdata_temp.txt");
        try(BufferedReader oldfile = new BufferedReader(new FileReader(inputFile));
        BufferedWriter newfile = new BufferedWriter(new FileWriter(tempFile)))
        {

        String line;
        while((line = oldfile.readLine())!=null)
        {
            if(line.contains(this.name))
            {
                line=newdata.name+" "+newdata.source+" "+newdata.destination+" "+newdata.distance+" "+newdata.seats+" "+newdata.availability+" ";
                list.add(line);
            }
            else
            {
                list.add(line);
            }
        }

        for(String s:list)
        {
            newfile.write(s);
            newfile.newLine();
        }
    }
    catch(IOException e)
        {
            System.out.println("Error");
        }
        if (!inputFile.delete()) {
            System.out.println("Could not delete original file");
            return;
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename temp file");
        }
    }

}