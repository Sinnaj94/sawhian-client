package main;

public class Tree {

    // Initial values of Aplha and Beta
    private final static float MAX = Float.POSITIVE_INFINITY;
    private final static float MIN = Float.NEGATIVE_INFINITY;

    /**
     *
     * MINMAX Alpha-Beta Pruning
     *
     * @param depth
     * @param nodeIndex
     * @param maximizingPlayer
     * @param values
     * @param alpha
     * @param beta
     * @return
     */
    static float prune(int depth, int nodeIndex,
                       Boolean maximizingPlayer,
                       float values[], float alpha,
                       float beta)
    {
        // Terminating condition. i.e
        // leaf node is reached
        if (depth == 3)
            return values[nodeIndex];

        if (maximizingPlayer)
        {
            float best = MIN;

            // Recur for left and
            // right children
            for (int i = 0; i < 2; i++)
            {
                float val = prune(depth + 1, nodeIndex * 2 + i,
                        false, values, alpha, beta);
                best = Math.max(best, val);
                alpha = Math.max(alpha, best);

                // Alpha Beta Pruning
                if (beta <= alpha)
                    break;
            }
            return best;
        }
        else
        {
            float best = MAX;

            // Recur for left and
            // right children
            for (int i = 0; i < 2; i++)
            {

                float val = prune(depth + 1, nodeIndex * 2 + i,
                        true, values, alpha, beta);
                best = Math.min(best, val);
                beta = Math.min(beta, best);

                // Alpha Beta Pruning
                if (beta <= alpha)
                    break;
            }
            return best;
        }
    }
}
