import java.util.*;

public class ElectionSystem {
    public static void main(String[] args) {
        Random rand = new Random();
        int numCandidates = 5 + rand.nextInt(6); // 5-10 candidates
        int electorateSize = 10 + rand.nextInt(11); // 10-20 votes

        LinkedList<String> candidates = new LinkedList<>();
        for (int i = 0; i < numCandidates; i++) {
            candidates.add("Candidate " + (i + 1));
        }

        Election election = new Election();
        election.initializeCandidates(candidates);
        election.setMaxVotes(electorateSize);

        System.out.println("Candidates: " + candidates);
        System.out.println("Electorate votes: " + electorateSize);

        // Random voting
        for (int i = 0; i < electorateSize; i++) {
            election.castRandomVote();
        }

        System.out.println("\nTop 3 candidates after " + election.getTotalVotes() + " votes: " +
                election.getTopKCandidates(3));


        System.out.println("\nFinal Audit:");
        election.auditElection();
    }
}
