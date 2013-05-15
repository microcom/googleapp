;; First define templates for the model classes so we can use them
;; in our pricing rules. This doesn't create any model objects --
;; it just tells Jess to examine the classes and set up templates
;; using their properties

(import com.auphelia.models.Contact)
(import org.codehaus.jettison.json.JSONObject)

(deftemplate Contact (declare (from-class Contact)))
(deftemplate JSONObject (declare (from-class JSONObject)))

(defrule validation-code-postal
    "Validation code postal"
    ?c <- (Contact (codePostal ?cp))
    ?jo <- (JSONObject)
	(test (not (regexp "^[ABCEGHJKLMNPRSTVXYabceghjklmnprstvxy]{1}\\d{1}[A-Za-z]{1} *\\d{1}[A-Za-z]{1}\\d{1}$" ?cp)))
    =>
    ; (printout t "Code postal invalide : " ?cp crlf)
	; (modify ?c (codePostal "L9L9L9"))
	; (retract ?c)
    (call (get ?jo OBJECT) accumulate "codePostal" "Invalid postal code")
	)

;(defrule normalisation-code-postal
;	"Normalisation code postal"
;	?c <- (Contact (codePostal ?cp))
;	(test (not (regexp "^[ABCEGHJKLMNPRSTVXY]{1}\\d{1}[A-Z]{1}\\d{1}[A-Z]{1}\\d{1}$" ?cp)))
;	=>
;	(printout t "Code postal irrégulier : " ?cp crlf)
;	(modify ?c (codePostal (call (call (get ?c codePostal) toUpperCase) replace " " "")))
;	)
