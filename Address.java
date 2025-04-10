package as;

class Address {
    private String streetNumber;
    private String streetName;
    private String parish;
    private String country;

    public Address(String fullAddress) {
        // Initialize with empty strings to avoid null values
        this.streetNumber = "";
        this.streetName = "";
        this.parish = "";
        this.country = "";

        if (fullAddress != null && !fullAddress.isEmpty()) {
            String[] parts = fullAddress.split(", ", 3); // Split into max 3 parts

            // Parse street number/name
            if (parts.length >= 1) {
                String[] streetParts = parts[0].split(" ", 2);
                if (streetParts.length >= 1) this.streetNumber = streetParts[0].trim();
                if (streetParts.length >= 2) this.streetName = streetParts[1].trim();
            }

            // Parse parish and country
            if (parts.length >= 2) this.parish = parts[1].trim();
            if (parts.length >= 3) this.country = parts[2].trim();
        }
    }

    @Override
    public String toString() {
        return getFullAddress();
    }

    public String getFullAddress() {
        StringBuilder sb = new StringBuilder();
        if (!streetNumber.isEmpty()) sb.append(streetNumber).append(" ");
        if (!streetName.isEmpty()) sb.append(streetName);
        if (!parish.isEmpty()) sb.append(", ").append(parish);
        if (!country.isEmpty()) sb.append(", ").append(country);
        return sb.toString().trim();
    }
}
