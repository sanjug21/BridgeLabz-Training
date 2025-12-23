public class SurroundedRegion {
    
        int n, m;
        public static void main(String[] args) {
            SurroundedRegion sr = new SurroundedRegion();
            char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
            };
            sr.solve(board);
            for (char[] row : board) {
                System.out.println(java.util.Arrays.toString(row));
            }
        }

        public void solve(char[][] board) {
            if (board == null || board.length == 0)
                return;
            n = board.length;
            m = board[0].length;
            for (int i = 0; i < n; i++) {
                dfs(board, i, 0);
                dfs(board, i, m - 1);
            }
            for (int j = 0; j < m; j++) {
                dfs(board, 0, j);
                dfs(board, n - 1, j);
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 'O')
                        board[i][j] = 'X';
                    if (board[i][j] == '#')
                        board[i][j] = 'O';
                }
            }
        }

        private void dfs(char[][] board, int i, int j) {
            if (i < 0 || j < 0 || i >= n || j >= m || board[i][j] != 'O')
                return;
            board[i][j] = '#';
            dfs(board, i + 1, j);
            dfs(board, i - 1, j);
            dfs(board, i, j + 1);
            dfs(board, i, j - 1);
        }
}


