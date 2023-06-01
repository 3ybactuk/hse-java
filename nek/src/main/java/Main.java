import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long N = scanner.nextLong();
        long K = scanner.nextLong();

        TreeMap<Long, Long> trains = new TreeMap<>();

        int n = 0; // occupied tunnels
        for (int i = 0; i < N; ++i) {
            long arrive = scanner.nextLong();
            long depart = scanner.nextLong();

            if (n >= K) {
                ArrayList<Long> deleteList = new ArrayList<>();

                for (Map.Entry<Long, Long> entry : trains.entrySet()) {
                    long departureTime = entry.getKey();
                    long numberOfTrains = entry.getValue();

                    if (departureTime > depart) {
                        break;
                    }

                    n -= numberOfTrains;
                    deleteList.add(departureTime);
                }
            }

            if (n < K) {
                trains.putIfAbsent(depart, 0L);
                trains.put(depart, trains.get(depart) + 1);
                n++;
            } else {
                System.out.println(0 + " " + (i + 1));
                return;
            }
        }
    }
}
