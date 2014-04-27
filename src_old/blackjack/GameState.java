/**
 * 
 */
package blackjack;

/**
 * These are all of the possible states that a hand can be in. For instance, a freshly dealt hand is
 * in state "DEAL".
 * 
 * @author Jordan King
 *
 */
public enum GameState {
	
	//The states the hands can be in
	BET,
	DEAL,
	HIT,
	STAND,
	SPLIT,
	DOUBLE,
	SURRENDER,
	RESOLVED,
	END;
	
}
