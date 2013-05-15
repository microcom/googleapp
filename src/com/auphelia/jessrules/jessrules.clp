;; First define templates for the model classes so we can use them
;; in our pricing rules. This doesn't create any model objects --
;; it just tells Jess to examine the classes and set up templates
;; using their properties

(import com.auphelia.models.Contact)

(deftemplate Contact (declare (from-class Contact)))

(defrule validation-code-postal
    "Validation code postal"
    ?c <- (Contact (codePostal ?cp))   ; le binding ?c ne sert à rien, c'est pour l'exemple
	(test (not (regexp "^[ABCEGHJKLMNPRSTVXYabceghjklmnprstvxy]{1}\\d{1}[A-Za-z]{1} *\\d{1}[A-Za-z]{1}\\d{1}$" ?cp)))
    =>
    (printout t "Code postal invalide : " ?cp crlf)
    (retract ?c)
	)

(defrule normalisation-code-postal
	"Normalisation code postal"
	?c <- (Contact (codePostal ?cp))
	(test (not (regexp "^[ABCEGHJKLMNPRSTVXY]{1}\\d{1}[A-Z]{1}\\d{1}[A-Z]{1}\\d{1}$" ?cp)))
	=>
	(printout t "Code postal irrégulier : " ?cp crlf)
	(modify ?c (codePostal (call (call (get ?c codePostal) toUpperCase) replace " " "")))
	)
