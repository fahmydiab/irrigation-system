package com.example.irrigationsystem.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

@NamedEntityGraphs({
    @NamedEntityGraph(name = "plot.details",
        attributeNodes = {
            @NamedAttributeNode("crop"),
            @NamedAttributeNode(value = "plotTimeSlots", subgraph = "plot.timeSlots")
        },
        subgraphs = {
            @NamedSubgraph(
                name = "plot.timeSlots",
                attributeNodes = {
                    @NamedAttributeNode("status"),
                    @NamedAttributeNode("startDateTime")
                }
            )
        }
    ),
    @NamedEntityGraph(name = "plot.list",
        attributeNodes = {
            @NamedAttributeNode("crop"),
            @NamedAttributeNode(value = "plotTimeSlots")
        }
    )
})
@Entity
@Table(name = "plots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@EqualsAndHashCode(callSuper = false)
public class Plot extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(scale = 2)
    private BigDecimal area;

    private LocalTime startTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_Id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Crop crop;

    @OneToMany(
        mappedBy = "plot",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JsonManagedReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<PlotTimeSlot> plotTimeSlots;

    public Plot(int i) {

    }

    public void irrigate(PlotTimeSlot slot) {
        try {
            sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            slot.setStatus(PlotTimeSlotEnum.SENT);
        }
    }
}
