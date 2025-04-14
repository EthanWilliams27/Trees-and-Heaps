import java.util.*;

public class Election {
    private Map<String, Integer> voteCount;
    private int totalVotes;
    private int maxVotes;
    private Random random;
    private List<String> candidates;

    public Election() {
        voteCount = new HashMap<>();
        random = new Random();
        totalVotes = 0;
    }

    public void initializeCandidates(LinkedList<String> candidates) {
        this.candidates = new ArrayList<>(candidates);
        for (String candidate : candidates) {
            voteCount.put(candidate, 0);
        }
    }

    public void castVote(String candidate) {
        if (!voteCount.containsKey(candidate)) return;
        voteCount.put(candidate, voteCount.get(candidate) + 1);
        totalVotes++;
    }

    public void castRandomVote() {
        int idx = random.nextInt(candidates.size());
        castVote(candidates.get(idx));
    }

    public void rigElection(String candidate) {
        if (!voteCount.containsKey(candidate)) return;
        voteCount.replaceAll((k, v) -> 0); // reset votes
        voteCount.put(candidate, maxVotes); // rig
        totalVotes = maxVotes;
    }

    public List<String> getTopKCandidates(int k) {
        PriorityQueue<Map.Entry<String, Integer>> maxHeap =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        maxHeap.addAll(voteCount.entrySet());

        List<String> topK = new ArrayList<>();
        while (k-- > 0 && !maxHeap.isEmpty()) {
            topK.add(maxHeap.poll().getKey());
        }
        return topK;
    }

    public void auditElection() {
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(voteCount.entrySet());
        sortedEntries.sort((a, b) -> b.getValue() - a.getValue());

        for (Map.Entry<String, Integer> entry : sortedEntries) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    public void setMaxVotes(int p) {
        this.maxVotes = p;
    }

    public int getTotalVotes() {
        return totalVotes;
    }
}
