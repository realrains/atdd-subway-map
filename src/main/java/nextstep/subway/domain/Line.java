package nextstep.subway.domain;

import nextstep.subway.exception.InvalidDownStationException;
import nextstep.subway.exception.InvalidUpStationException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Line extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String color;
    @OneToMany(mappedBy = "line", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Section> sections = new ArrayList<>();

    protected Line() {
    }

    public Line(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void change(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public void addSection(Section section) {
        if (!sections.isEmpty()) {
            validateNewSection(section);
        }

        this.sections.add(section);
        section.setLine(this);
    }

    private void validateNewSection(Section section) {
        validateUpStation(section.getUpStation());
        validateDownStation(section.getDownStation());
    }

    private void validateUpStation(Station upStation) {
        boolean isConnectedStation = getLastDownStation().equals(upStation);

        if (!isConnectedStation) {
            throw new InvalidUpStationException(upStation.getName());
        }
    }

    private Station getLastDownStation() {
        return getLastSection().getDownStation();
    }

    private Section getLastSection() {
        return sections.get(sections.size() - 1);
    }

    private void validateDownStation(Station downStation) {
        sections.stream()
                .filter(s -> s.contains(downStation))
                .findAny()
                .ifPresent(s -> {
                    throw new InvalidDownStationException(downStation.getName());
                });
    }
}
