import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/*
** LOH FAH YAO, JARYL A0202023H
 */

public class Main {
    public static void main(String[] args) throws IOException {
        TreeMap<Quest, Integer> tmap = new TreeMap<Quest, Integer>(new MyComparator());

        Reader r = new Reader();
        int n = r.nextInt();

        for(int i = 0; i < n; i++) {
            char cmd = r.nextWord();
            if(cmd == 'a') {
                Integer dummy;
                int energy = r.nextInt();
                int gold = r.nextInt();
                Quest quest = new Quest(energy, gold);
                if((dummy = tmap.get(quest)) == null) tmap.put(quest, 1);
                else tmap.put(quest, dummy + 1);
            } else if(cmd == 'q') {
                long seekGold = 0;
                int energyLeft = r.nextInt();
                while(energyLeft > 0) {
                    Map.Entry<Quest, Integer> entry;
                    if((entry = tmap.floorEntry(new Quest(energyLeft, Integer.MAX_VALUE))) == null) break;
                    else {
                        if(entry.getValue() == 1) tmap.remove(entry.getKey());
                        else tmap.put(entry.getKey(), entry.getValue() - 1);

                        energyLeft -= entry.getKey().energy;
                        seekGold += entry.getKey().gold;
                    }
                }
                System.out.println(seekGold);
            }
        }
    }

    static class MyComparator implements Comparator<Quest> {
        public int compare(Quest a, Quest b) {
            return a.energy - b.energy == 0 ? a.gold - b.gold : a.energy - b.energy;
        }
    }
}
