package library_management_system;

abstract class Member extends User implements FineCalculator {
    public Member(String name, String memberId) {
        super(name, memberId);
    }

    public String getMemberId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
