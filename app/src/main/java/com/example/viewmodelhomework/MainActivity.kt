import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Postavljamo početni fragment ako nema saved instance
        if (savedInstanceState == null) {
            // Možete promeniti početni fragment po želji
            showTicTacToeFragment()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()

        if (id == R.id.menu_tic_tac_toe) {
            showTicTacToeFragment()
            return true
        } else if (id == R.id.menu_text_game) {
            showTextGameFragment()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showTicTacToeFragment() {
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, TicTacToeFragment())
            .commit()
    }

    private fun showTextGameFragment() {
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, TextGameFragment())
            .commit()
    }
}