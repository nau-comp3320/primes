(ns primes.core)

(defn divisible-by?
  "Returns true if n is divisible by m."
  [n m]
  (zero? (rem n m)))

(defn primes-up-to-n
  "Returns all of the prime numbers up to n."
  [n]
  (loop [i 2
         primes []]
    (if (> i n)
      primes
      (if (some (fn [p] (divisible-by? i p)) primes)
        (recur (inc i) primes)
        (recur (inc i) (conj primes i))))))

(defn next-prime
  "Helper function to create lazy sequences of prime numbers."
  [i primes]
  (lazy-seq 
    (if (some (fn [p] (divisible-by? i p)) primes)
      (next-prime (inc i) primes)
      (cons i (next-prime (inc i) (conj primes i))))))

(defn primes
  "Returns a lazy sequence of all the prime numbers."
  []
  (next-prime 2 []))

(def prime-seq (primes))
